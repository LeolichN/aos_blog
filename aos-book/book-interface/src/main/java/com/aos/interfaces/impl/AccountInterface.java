package com.aos.interfaces.impl;

import com.aos.application.service.IAccountApplication;
import com.aos.core.response.BaseResponse;
import com.aos.core.response.PageResponse;
import com.aos.interfaces.IAccountInterface;
import com.aos.repo.dto.AccountOperateDTO;
import com.aos.repo.dto.AccountQueryDTO;
import com.aos.repo.dto.AdjustBalanceAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AccountInterface implements IAccountInterface {

    @Autowired
    private IAccountApplication accountApplication;

    @Override
    public BaseResponse handleOverview() {
        return accountApplication.overview();
    }

    @Override
    public BaseResponse handleStatistics(AccountQueryDTO accountQueryDTO) {
        return accountApplication.statistics(accountQueryDTO);
    }

    @Override
    public BaseResponse handleAdd(AccountOperateDTO accountAddDTO) {
        return accountApplication.add(accountAddDTO);
    }

    @Override
    public BaseResponse handleAll(AccountQueryDTO accountQueryDTO) {
        return accountApplication.all(accountQueryDTO);
    }

    @Override
    public PageResponse handleQuery(AccountQueryDTO form, Pageable page) {
        return accountApplication.query(form,page);
    }

    @Override
    public BaseResponse handleUpdate(Integer id, AccountOperateDTO operateDTO) {
        return accountApplication.update(id,operateDTO);
    }

    @Override
    public BaseResponse handleDelete(Integer id) {
        return accountApplication.delete(id);
    }

    @Override
    public BaseResponse handleAdjust(Integer id, AdjustBalanceAddDTO form) {
        return accountApplication.adjust(id,form);
    }

    @Override
    public BaseResponse handleUpdateAdjust(Integer id, AdjustBalanceAddDTO form) {
        return accountApplication.adjust(id,form);
    }


}
