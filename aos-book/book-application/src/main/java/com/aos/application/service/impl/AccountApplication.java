package com.aos.application.service.impl;

import com.aos.application.service.IAccountApplication;
import com.aos.core.response.BaseResponse;
import com.aos.core.response.DataResponse;
import com.aos.core.response.PageResponse;
import com.aos.domain.service.IAccountService;
import com.aos.repo.dto.AccountOperateDTO;
import com.aos.repo.dto.AccountQueryDTO;
import com.aos.repo.dto.AdjustBalanceAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AccountApplication implements IAccountApplication {

    @Autowired
    private IAccountService accountService;


    @Override
    public DataResponse overview() {
        return new DataResponse(accountService.overview());
    }

    @Override
    public DataResponse statistics(AccountQueryDTO accountQueryDTO) {
        return new DataResponse(accountService.statistics(accountQueryDTO));
    }

    @Override
    public PageResponse query(AccountQueryDTO dto, Pageable page) {
        return new PageResponse(accountService.query(dto,page));
    }

    @Override
    public BaseResponse add(AccountOperateDTO accountOperateDTO) {
        return new BaseResponse(accountService.add(accountOperateDTO));
    }

    @Override
    public BaseResponse update(Integer id, AccountOperateDTO operateDTO) {
        return new BaseResponse(accountService.update(id,operateDTO));
    }

    @Override
    public BaseResponse delete(Integer id) {
        return new BaseResponse(accountService.delete(id));
    }

    @Override
    public BaseResponse adjust(Integer id, AdjustBalanceAddDTO form) {
        return new BaseResponse(accountService.adjust(id,form));
    }

    @Override
    public DataResponse all(AccountQueryDTO accountQueryDTO) {
        return new DataResponse(accountService.queryAll(accountQueryDTO));
    }
}
