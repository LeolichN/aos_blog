package com.aos.domain.service.impl;

import com.aos.core.exception.FailureMessageException;
import com.aos.core.exception.ItemNotFoundException;
import com.aos.core.utils.CollectionsUtils;
import com.aos.core.utils.DateUtils;
import com.aos.domain.service.*;
import com.aos.repo.dto.BalanceFlowOperateDTO;
import com.aos.repo.dto.BalanceFlowQueryDTO;
import com.aos.repo.dto.CategoryRelationDTO;
import com.aos.repo.entity.*;
import com.aos.repo.enums.FlowType;
import com.aos.repo.enums.Limitation;
import com.aos.repo.mapper.BalanceFlowMapper;
import com.aos.repo.repository.BalanceFlowRepo;
import com.aos.repo.vo.BalanceFlowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BalanceFlowService implements IBalanceFlowService {
    
    @Autowired
    private BalanceFlowRepo balanceFlowRepo;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private BookService bookService;

    @Override
    public boolean existsByBook(Book book) {
        return balanceFlowRepo.existsByBook(book);
    }

    private void checkBeforeAdd(BalanceFlowOperateDTO form, Book book, User user) {
        // 对用户添加账单的操作进行限流。
        long[] day = DateUtils.dayStartAndEnd(new Date());
        if (balanceFlowRepo.countByCreatorAndInsertAtBetween(user, day[0], day[1]) > Limitation.flow_max_count_daily) {
            throw new FailureMessageException("add.flow.daily.overflow");
        }
        Account account = null;
        if (form.getAccountId() != null) {
            account = accountService.findAccountById(form.getAccountId());
        }
        if (form.getType() == FlowType.EXPENSE || form.getType() == FlowType.INCOME) {
            // 支出和收入的分类不能为空，因为transfer可以为空，所以只能在这里验证
            if (CollectionUtils.isEmpty(form.getCategories())) {
                throw new FailureMessageException("add.flow.category.empty");
            }

            // 检查Category不能重复
            if (CollectionsUtils.duplicate(form.getCategories(),CategoryRelationDTO::getCategoryId)) {
                throw new FailureMessageException("add.flow.category.duplicated");
            }
            // 限制每个账单的分类数目
            if (form.getCategories().size() > Limitation.flow_max_category_count) {
                throw new FailureMessageException("add.flow.category.overflow");
            }
            // 外币账户必须输入convertedAmount
            if (account != null) {
                if (!account.getCurrencyCode().equals(book.getDefaultCurrencyCode())) {
                    form.getCategories().forEach(i -> {
                        if (i.getConvertedAmount() == null) throw new FailureMessageException("valid.fail");
                    });
                }
            }
        }
        if (form.getType() == FlowType.TRANSFER) {
            // 转账必须有account, to id和amount
            if (account == null || form.getToId() == null || form.getAmount() == null) {
                throw new FailureMessageException("valid.fail");
            }
            // 转账的两个账户的币种不同，必须输入convertedAmount
            Account toAccount = accountService.findAccountById(form.getToId());
            if (!account.getCurrencyCode().equals(toAccount.getCurrencyCode())) {
                if (form.getConvertedAmount() == null) {
                    throw new FailureMessageException("valid.fail");
                }
            }
        }
    }

    private void checkBeforeUpdate(BalanceFlowOperateDTO form, BalanceFlow entity, Book book) {
        Account account = entity.getAccount();
        if(!Objects.equals(entity.getAccount() != null ? entity.getAccount().getId() : null, form.getAccountId())) {
            account = accountService.findAccountById(form.getAccountId());
        }
        if (entity.getType() == FlowType.EXPENSE || entity.getType() == FlowType.INCOME) {
            // 因为Category为非空，所以更新传入空代表不修改。
            // 检查Category不能重复
            if (CollectionsUtils.duplicate(form.getCategories(),CategoryRelationDTO::getCategoryId)) {
                throw new FailureMessageException("add.flow.category.duplicated");
            }
            // 限制每个账单的分类数目
            if (form.getCategories() != null && form.getCategories().size() > Limitation.flow_max_category_count) {
                throw new FailureMessageException("add.flow.category.overflow");
            }
            // 外币账户必须输入convertedAmount
            // account修改了才需要判断，account没有修改的，也需要判断是不是外币账户。
            if (account != null) {
                if (!account.getCurrencyCode().equals(book.getDefaultCurrencyCode())) {
                    // 不传代表不修改，所以需要判断非空
                    if (!CollectionUtils.isEmpty(form.getCategories())) {
                        form.getCategories().forEach(i -> {
                            if (i.getConvertedAmount() == null) throw new FailureMessageException("valid.fail");
                        });
                    }
                }
            }
        }
        if (entity.getType() == FlowType.TRANSFER) {
            // 转账必须有to id和amount
//            if (form.getToId() == null || form.getAmount() == null) {
//                throw new FailureMessageException("valid.fail");
//            }
            // 转账的两个账户的币种不同，必须输入convertedAmount
            // 账户有修改才需要判断，账户没有修改，但是之前两个账户的currencyCode不同也需要convertedAmount
            Account toAccount = entity.getTo();
            if (form.getToId() != null && !form.getToId().equals(entity.getTo().getId())) {
                toAccount = accountService.findAccountById(form.getToId());
            }
            if (!account.getCurrencyCode().equals(toAccount.getCurrencyCode())) {
                if (form.getConvertedAmount() == null) {
                    throw new FailureMessageException("valid.fail");
                }
            }
        }
    }

    @Override
    public boolean add(BalanceFlowOperateDTO form) {
        User user = userService.getCurrentUser();
        Group group = groupService.getUserDefaultGroup(user);
        Book book = bookService.getBook(form.getBookId());
        checkBeforeAdd(form, book, user);
        BalanceFlow entity = BalanceFlowMapper.MAPPER.toBalanceFlow(form);
        entity.setGroup(group);
        entity.setBook(book);
        entity.setCreator(user);
        if (form.getAccountId() != null) {
            entity.setAccount(accountService.findAccountById(form.getAccountId()));
        }
        if (form.getType() == FlowType.EXPENSE || form.getType() == FlowType.INCOME) {
            BigDecimal amount = form.getCategories().stream().map(CategoryRelationDTO::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            entity.setAmount(amount);
            if (entity.getAccount() == null || entity.getAccount().getCurrencyCode().equals(book.getDefaultCurrencyCode())) {
                entity.setConvertedAmount(amount);
            } else {
                BigDecimal convertedAmount = form.getCategories().stream().map(CategoryRelationDTO::getConvertedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                entity.setConvertedAmount(convertedAmount);
            }
            categoryRelationService.addRelation(form.getCategories(), entity, book, entity.getAccount());
        }
        if (form.getType() == FlowType.TRANSFER) {
            Account toAccount = accountService.findAccountById(form.getToId());
            entity.setTo(toAccount);
            if (entity.getAccount().getCurrencyCode().equals(entity.getTo().getCurrencyCode())) {
                entity.setConvertedAmount(entity.getAmount());
            } else {
                entity.setConvertedAmount(form.getConvertedAmount());
            }
        }
        if (form.getTags() != null) {
            tagRelationService.addRelation(form.getTags(), entity, book, entity.getAccount());
        }
        if (form.getPayeeId() != null) {
            Payee payee = payeeRepository.findOneByBookAndId(book, form.getPayeeId()).orElseThrow(ItemNotFoundException::new);
            entity.setPayee(payee);
        }
        balanceFlowRepo.save(entity);
        if (form.getConfirm()) {
            confirmBalance(entity);
        }
        return true;
    }

    // 余额确认，包括账户扣款。
    private void confirmBalance(BalanceFlow flow) {
        if (flow.getConfirm() && flow.getAccount() != null) {
            Account account = flow.getAccount();
            if (flow.getType() == FlowType.EXPENSE) {
                account.setBalance(account.getBalance().subtract(flow.getAmount()));
            } else if (flow.getType() == FlowType.INCOME) {
                account.setBalance(account.getBalance().add(flow.getAmount()));
            } else if (flow.getType() == FlowType.TRANSFER) {
                Account toAccount = flow.getTo();
                BigDecimal amount = flow.getAmount();
                BigDecimal convertedAmount = flow.getConvertedAmount();
                account.setBalance(account.getBalance().subtract(amount));
                toAccount.setBalance(toAccount.getBalance().add(convertedAmount));
                accountService.save(toAccount);
            }
            accountService.save(account);
        }
    }

    private void refundBalance(BalanceFlow flow) {
        if (flow.getConfirm() && flow.getAccount() != null) {
            Account account = flow.getAccount();
            if (flow.getType() == FlowType.EXPENSE) {
                account.setBalance(account.getBalance().add(flow.getAmount()));
            } else if (flow.getType() == FlowType.INCOME) {
                account.setBalance(account.getBalance().subtract(flow.getAmount()));
            } else if (flow.getType() == FlowType.TRANSFER) {
                Account toAccount = flow.getTo();
                BigDecimal amount = flow.getAmount();
                BigDecimal convertedAmount = flow.getConvertedAmount();
                account.setBalance(account.getBalance().add(amount));
                toAccount.setBalance(toAccount.getBalance().subtract(convertedAmount));
                accountService.save(toAccount);
            } else if (flow.getType() == FlowType.ADJUST) {
                account.setBalance(account.getBalance().subtract(flow.getAmount()));
            }
            accountService.save(account);
        }
    }

    @Override
    @Transactional(readOnly = true)
    // 不加transaction会报错
    // org.hibernate.LazyInitializationException: could not initialize proxy [tech.jiukuai.bookkeeping.user.account.Account#1] - no Session
    public Page<BalanceFlowVO> query(BalanceFlowQueryDTO request, Pageable page) {
        Group group = groupService.getCurrentUserDefaultGroup();
        Page<BalanceFlow> entityPage = balanceFlowRepo.findAll(request.buildPredicate(group), page);
        return entityPage.map(balanceFlowMapper::toDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public BalanceFlowVO get(Integer id) {
        return balanceFlowMapper.toDetails(baseService.findFlowById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal[] statistics(BalanceFlowQueryDTO request) {
        var result = new BigDecimal[3];
        Group group = groupService.getCurrentUserDefaultGroup();
        QBalanceFlow balanceFlow = QBalanceFlow.balanceFlow;
        result[0] = balanceFlowRepo.calcSum(request.buildExpensePredicate(group), balanceFlow.convertedAmount, balanceFlow);
        result[1] = balanceFlowRepo.calcSum(request.buildIncomePredicate(group), balanceFlow.convertedAmount, balanceFlow);
        result[2] = result[1].subtract(result[0]);
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        BalanceFlow entity = balanceFlowRepo.findById(id).orElseThrow(ItemNotFoundException::new);
        refundBalance(entity);
        balanceFlowRepo.delete(entity);
        return true;
    }

    private boolean categoryEquals(Set<CategoryRelation> categories1, List<CategoryRelationDTO> categories2) {
        if (categories1.size() != categories2.size()) {
            return false;
        }
        for (var i : categories1) {
            boolean found = false;
            for (var j : categories2) {
                if (j.equals(i)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private boolean tagEquals(Set<Integer> tags1, Set<TagRelation> tags2) {
        if (tags1.size() != tags2.size()) {
            return false;
        }
        for (var i : tags2) {
            boolean found = false;
            for (var j : tags1) {
                if (j.equals(i.getTag().getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(Integer id, BalanceFlowOperateDTO form) {
        // checkBeforeUpdate
        BalanceFlow entity = baseService.findFlowById(id);
        Book book = entity.getBook();
        checkBeforeUpdate(form, entity, book);
        BalanceFlowMapper.updateEntity(form, entity);
        boolean refundFlag = false;
        Account newAccount = entity.getAccount();
        BigDecimal newAmount = entity.getAmount();
        BigDecimal newConvertedAmount = entity.getConvertedAmount();
        Account newTo = entity.getTo();
        // 判断账户是否更新
        if(!Objects.equals(entity.getAccount() != null ? entity.getAccount().getId() : null, form.getAccountId())) {
            newAccount = baseService.findAccountById(form.getAccountId());
            refundFlag = true;
        }
        if (entity.getType() == FlowType.EXPENSE || entity.getType() == FlowType.INCOME) {
            // 传入的categories为空，代表不修改。
            if (!CollectionUtils.isEmpty(form.getCategories()) && !categoryEquals(entity.getCategories(), form.getCategories())) {
                newAmount = form.getCategories().stream().map(CategoryRelationDTO::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                if (entity.getAmount().compareTo(newAmount) != 0) {
                    refundFlag = true;
                }
                if (newAccount == null || newAccount.getCurrencyCode().equals(book.getDefaultCurrencyCode())) {
                    newConvertedAmount = newAmount;
                } else {
                    newConvertedAmount = form.getCategories().stream().map(CategoryRelationDTO::getConvertedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                }
                if (entity.getAmount().compareTo(newAmount) != 0) {
                    // 不然更新金额，标签对应的金额不更新
                    // 只有更新金额才自动更新标签的金额，更新分类不更新标签的金额
                    for (TagRelation i : entity.getTags()) {
                        i.setAmount(newAmount);
                        i.setConvertedAmount(newConvertedAmount);
                    }
                }
                entity.getCategories().clear();
                categoryRelationService.addRelation(form.getCategories(), entity, book, newAccount);
            }
        } else if (entity.getType() == FlowType.TRANSFER) {
            if (form.getToId() != null && !form.getToId().equals(entity.getTo().getId())) {
                newTo = baseService.findAccountById(form.getToId());
                refundFlag = true;
            }
            if (form.getAmount() != null && form.getAmount().compareTo(entity.getAmount()) != 0) {
                newAmount = form.getAmount();
                refundFlag = true;
            }
            if (newAccount.getCurrencyCode().equals(newTo.getCurrencyCode())) {
                newConvertedAmount = newAmount;
            } else {
                // 这种情况form.getConvertedAmount()一定不为空
                if (form.getConvertedAmount().compareTo(entity.getConvertedAmount()) != 0) {
                    newConvertedAmount = form.getConvertedAmount();
                    refundFlag = true;
                }
            }
        }
        if (refundFlag && entity.getConfirm()) {
            refundBalance(entity);
        }
        entity.setAccount(newAccount);
        entity.setAmount(newAmount);
        entity.setTo(newTo);
        entity.setConvertedAmount(newConvertedAmount);
        if (form.getPayeeId() != null) {
            if (entity.getPayee() == null || !form.getPayeeId().equals(entity.getPayee().getId())) {
                Payee payee = payeeRepository.findOneByBookAndId(book, form.getPayeeId()).orElseThrow(ItemNotFoundException::new);
                entity.setPayee(payee);
            }
        } else {
            entity.setPayee(null);
        }
        // 传入null代表不修改，传入空数组[]，代表清空。
        if (form.getTags() != null && !tagEquals(form.getTags(), entity.getTags())) {
            entity.getTags().clear();
            tagRelationService.addRelation(form.getTags(), entity, book, newAccount);
        }
        balanceFlowRepo.save(entity);
        if (refundFlag && entity.getConfirm()) {
            confirmBalance(entity);
        }
        return true;
    }

    @Override
    public boolean confirm(Integer id) {
        BalanceFlow entity = baseService.findFlowById(id);
        entity.setConfirm(true);
        confirmBalance(entity);
        balanceFlowRepo.save(entity);
        return true;
    }
}
