package com.aos.application.service;

import com.aos.core.response.BaseResponse;
import com.aos.core.response.DataResponse;
import com.aos.core.response.PageResponse;
import com.aos.repo.dto.AccountOperateDTO;
import com.aos.repo.dto.AccountQueryDTO;
import com.aos.repo.dto.AdjustBalanceAddDTO;
import org.springframework.data.domain.Pageable;

public interface IAccountApplication {
    DataResponse overview();

    DataResponse statistics(AccountQueryDTO accountQueryDTO);

    PageResponse query(AccountQueryDTO form, Pageable page);

    BaseResponse add(AccountOperateDTO accountAddDTO);

    BaseResponse update(Integer id, AccountOperateDTO operateDTO);

    BaseResponse delete(Integer id);

    BaseResponse adjust(Integer id, AdjustBalanceAddDTO form);

    DataResponse all(AccountQueryDTO accountQueryDTO);
}
