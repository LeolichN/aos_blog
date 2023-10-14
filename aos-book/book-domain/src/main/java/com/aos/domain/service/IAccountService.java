package com.aos.domain.service;


import com.aos.repo.dto.AccountOperateDTO;
import com.aos.repo.dto.AccountQueryDTO;
import com.aos.repo.dto.AdjustBalanceAddDTO;
import com.aos.repo.vo.AccountVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    @Transactional(readOnly = true)
    List<BigDecimal> overview();

    BigDecimal[] statistics(AccountQueryDTO accountQueryDTO);

    Page<AccountVO> query(AccountQueryDTO dto, Pageable page);

    boolean add(AccountOperateDTO accountAddDTO);

    boolean update(Integer id, AccountOperateDTO operateDTO);

    boolean delete(Integer id);

    boolean adjust(Integer id, AdjustBalanceAddDTO form);
}
