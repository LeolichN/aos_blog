package com.aos.domain.service.impl;

import com.aos.core.exception.FailureMessageException;
import com.aos.core.exception.ItemExistsException;
import com.aos.core.exception.ItemNotFoundException;
import com.aos.domain.service.*;
import com.aos.repo.dto.AccountOperateDTO;
import com.aos.repo.dto.AccountQueryDTO;
import com.aos.repo.dto.AdjustBalanceAddDTO;
import com.aos.repo.entity.Account;
import com.aos.repo.entity.BalanceFlow;
import com.aos.repo.entity.Group;
import com.aos.repo.enums.AccountType;
import com.aos.repo.enums.FlowType;
import com.aos.repo.enums.Limitation;
import com.aos.repo.mapper.AccountMapper;
import com.aos.repo.repository.AccountRepo;
import com.aos.repo.repository.BalanceFlowRepo;
import com.aos.repo.vo.AccountVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AccountService implements IAccountService {
    @Autowired
    private ICurrencyService currencyService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private BookService bookService;
    @Autowired
    private BalanceFlowRepo balanceFlowRepo;

    @Override
    public List<BigDecimal> overview() {
        BigDecimal[] result = new BigDecimal[3];
        Arrays.fill(result,BigDecimal.ZERO);

        Group group = groupService.getCurrentUserDefaultGroup();

        List<Account> assetAccounts = getAssets(group);

        if (!CollectionUtils.isEmpty(assetAccounts)) {
            BigDecimal assetBalance = assetAccounts.stream().map(account -> currencyService.convert(account.getBalance(), account.getCurrencyCode(), group.getDefaultCurrencyCode())).reduce(BigDecimal.ZERO,BigDecimal::add);
            result[0] = assetBalance;
        }

        List<Account> debtAccounts = getDebts(group);
        if (!CollectionUtils.isEmpty(debtAccounts)) {
            BigDecimal debtBalance = debtAccounts.stream().map(account -> currencyService.convert(account.getBalance(), account.getCurrencyCode(), group.getDefaultCurrencyCode())).reduce(BigDecimal.ZERO,BigDecimal::add);
            result[0] = debtBalance;
        }
        result[0] =  result[0].subtract(result[1]);
        return Arrays.asList(result);
    }

    @Override
    public BigDecimal[] statistics(AccountQueryDTO accountQueryDTO) {
        BigDecimal[] result = new BigDecimal[3];
        Arrays.fill(result,BigDecimal.ZERO);
        Group group = groupService.getCurrentUserDefaultGroup();
        List<Account> accounts = accountRepo.findAll(accountQueryDTO.buildPredicate(group));

        if (!CollectionUtils.isEmpty(accounts)) {
            BigDecimal balance = accounts.stream().map(account -> currencyService.convert(account.getBalance(), account.getCurrencyCode(), group.getDefaultCurrencyCode())).reduce(BigDecimal.ZERO,BigDecimal::add);
            result[0] = balance;
            BigDecimal creditLimit = accounts.stream().filter(account -> account.getCreditLimit() != null).map(account -> currencyService.convert(account.getBalance(), account.getCurrencyCode(), group.getDefaultCurrencyCode())).reduce(BigDecimal.ZERO, BigDecimal::add);
            result[1] = creditLimit;
            result[2] = creditLimit.add(balance);
        }
        return result;
    }

    @Override
    public Page<AccountVO> query(AccountQueryDTO dto, Pageable page) {
        Group group = groupService.getCurrentUserDefaultGroup();
        Page<Account> entityPage = accountRepo.findAll(dto.buildPredicate(group), page);
        return entityPage.map(account -> {
            AccountVO details = AccountMapper.MAPPER.toAccountVO(account);
            details.setConvertedBalance(currencyService.convert(details.getBalance(), details.getCurrencyCode(), group.getDefaultCurrencyCode()));
//            details.setTypeName(EnumU.translateAccountType(details.getType()));
            return details;
        });
    }

    @Override
    public boolean add(AccountOperateDTO accountAddDTO) {
        Group group = groupService.getCurrentUserDefaultGroup();
        int countByGroup = accountRepo.countByGroup(group);
        if (countByGroup >= Limitation.account_max_count) {
            throw new FailureMessageException("account.max.count");
        }
        boolean existsByGroupAndName = accountRepo.existsByGroupAndName(group, accountAddDTO.getName());
        if (existsByGroupAndName) {
            throw new ItemExistsException();
        }
        Account account = AccountMapper.MAPPER.toAccount(accountAddDTO);
        if (!StringUtils.isNoneBlank(accountAddDTO.getCurrencyCode())) {
            account.setCurrencyCode(group.getDefaultCurrencyCode());
        }
        // todo fill currency things
//        if (!Objects.equals(group.getDefaultCurrencyCode(), account.getCurrencyCode())) {
//            currencyService.checkCode(form.getCurrencyCode());
//        }
        account.setGroup(group);
        accountRepo.save(account);
        return true;
    }

    @Override
    public boolean update(Integer id, AccountOperateDTO operateDTO) {
        Group group = groupService.getCurrentUserDefaultGroup();
        Account account = accountRepo.findById(id).orElseThrow(() -> new FailureMessageException("account.not.exist"));
        if (!account.getName().equals(operateDTO.getName())) {
            if (StringUtils.isNoneBlank(operateDTO.getName())) {
                if (accountRepo.existsByGroupAndName(group, operateDTO.getName())) {
                    throw new ItemExistsException();
                }
            }
        }
        account.setName( operateDTO.getName() );

        account.setNotes( operateDTO.getNotes() );
        account.setNo( operateDTO.getNo() );
        if ( operateDTO.getInclude() != null ) {
            account.setInclude( operateDTO.getInclude() );
        }
        if ( operateDTO.getCanExpense() != null ) {
            account.setCanExpense( operateDTO.getCanExpense() );
        }
        if ( operateDTO.getCanIncome() != null ) {
            account.setCanIncome( operateDTO.getCanIncome() );
        }
        if ( operateDTO.getCanTransferFrom() != null ) {
            account.setCanTransferFrom( operateDTO.getCanTransferFrom() );
        }
        if ( operateDTO.getCanTransferTo() != null ) {
            account.setCanTransferTo( operateDTO.getCanTransferTo() );
        }
        account.setCreditLimit( operateDTO.getCreditLimit() );
        account.setBillDay( operateDTO.getBillDay() );
        account.setApr( operateDTO.getApr() );
        accountRepo.save(account);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new FailureMessageException("account.not.exist"));
        account.setDeleted(true);
        account.setEnable(false);
        account.setInclude(false);
        account.setCanExpense(false);
        account.setCanIncome(false);
        account.setCanTransferFrom(false);
        account.setCanTransferTo(false);
        accountRepo.save(account);
        return true;
    }

    @Override
    public boolean adjust(Integer id, AdjustBalanceAddDTO adjustBalanceAddDTO) {
        Group group = groupService.getCurrentUserDefaultGroup();
        Account account = accountRepo.findById(id).orElseThrow(() -> new FailureMessageException("account.not.exist"));
        BigDecimal adjustAmount = adjustBalanceAddDTO.getBalance().subtract(account.getBalance());
        // 余额没有变化
        if (adjustAmount.signum() == 0) throw new FailureMessageException("account.adjust.balance.same");
        account.setBalance(adjustBalanceAddDTO.getBalance());
        accountRepo.save(account);
        BalanceFlow flow = new BalanceFlow();
        flow.setType(FlowType.ADJUST);
        flow.setGroup(group);
        flow.setCreator(userService.getCurrentUser());
        flow.setBook(bookService.getBook(adjustBalanceAddDTO.getBookId()));
        flow.setAccount(account);
        flow.setAmount(adjustAmount);
        flow.setTitle(adjustBalanceAddDTO.getTitle());
        flow.setNotes(adjustBalanceAddDTO.getNotes());
        flow.setCreateTime(adjustBalanceAddDTO.getCreateTime());
        flow.setConfirm(true);
        balanceFlowRepo.save(flow);
        return true;
    }

    @Override
    public List<AccountVO> queryAll(AccountQueryDTO accountQueryDTO) {
        Group group = groupService.getCurrentUserDefaultGroup();
        List<Account> accounts = accountRepo.findAll(accountQueryDTO.buildPredicate(group));
        return accounts.stream().map(account -> AccountMapper.MAPPER.toAccountVO(account)).collect(Collectors.toList());
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountRepo.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    private List<Account> getAssets(Group group) {
        List<Account> assetAccounts = new ArrayList<>();
        List<Account> checkingAssets = accountRepo.findAllByGroupAndTypeAndEnableAndInclude(group, AccountType.CHECKING, true, true);
        assetAccounts.addAll(checkingAssets);
        List<Account> assets = accountRepo.findAllByGroupAndTypeAndEnableAndInclude(group, AccountType.ASSET, true, true);
        assetAccounts.addAll(assets);
        return assetAccounts;
    }


    private List<Account> getDebts(Group group) {
        List<Account> debtAccounts = new ArrayList<>();
        List<Account> credits = accountRepo.findAllByGroupAndTypeAndEnableAndInclude(group, AccountType.CREDIT, true, true);
        debtAccounts.addAll(credits);
        List<Account> debts = accountRepo.findAllByGroupAndTypeAndEnableAndInclude(group, AccountType.DEBT, true, true);
        debtAccounts.addAll(debts);
        return debtAccounts;
    }
}
