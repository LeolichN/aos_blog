package com.aos.interfaces.impl;

import com.aos.application.service.IBalanceFlowApplication;
import com.aos.core.response.BaseResponse;
import com.aos.interfaces.IBalanceFlowInterface;
import com.aos.repo.dto.BalanceFlowOperateDTO;
import com.aos.repo.dto.BalanceFlowQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BalanceFlowInterface implements IBalanceFlowInterface {
    @Autowired
    private IBalanceFlowApplication balanceFlowApplication;

    @Override
    public BaseResponse handleAdd(BalanceFlowOperateDTO form) {
        return balanceFlowApplication.add(form);
    }

    @Override
    public BaseResponse handleQuery(BalanceFlowQueryDTO form, Pageable page) {
        return balanceFlowApplication.query(form,page);
    }

    @Override
    public BaseResponse handleGet(Integer id) {
        return balanceFlowApplication.get(id);
    }

    @Override
    public BaseResponse handleUpdate(Integer id, BalanceFlowOperateDTO form) {
        return balanceFlowApplication.update(id,form);
    }

    @Override
    public BaseResponse handleDelete(Integer id) {
        return balanceFlowApplication.delete(id);
    }

    @Override
    public BaseResponse handleStatistics(BalanceFlowQueryDTO form) {
        return balanceFlowApplication.statistic(form);
    }

    @Override
    public BaseResponse handleConfirm(Integer id) {
        return balanceFlowApplication.confirm(id);
    }
}
