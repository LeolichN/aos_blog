package com.aos.domain.service;

import com.aos.repo.dto.BalanceFlowOperateDTO;
import com.aos.repo.dto.BalanceFlowQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BalanceFlowVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface IBalanceFlowService {
    boolean existsByBook(Book book);

    boolean add(BalanceFlowOperateDTO form);

    Page<BalanceFlowVO> query(BalanceFlowQueryDTO form, Pageable page);

    BalanceFlowVO get(Integer id);

    boolean update(Integer id, BalanceFlowOperateDTO form);

    boolean delete(Integer id);

    BigDecimal[] statistics(BalanceFlowQueryDTO form);

    boolean confirm(Integer id);
}
