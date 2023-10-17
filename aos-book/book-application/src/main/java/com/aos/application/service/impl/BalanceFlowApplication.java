package com.aos.application.service.impl;

import com.aos.application.service.IBalanceFlowApplication;
import com.aos.core.response.BaseResponse;
import com.aos.core.response.DataResponse;
import com.aos.core.response.PageResponse;
import com.aos.domain.service.IBalanceFlowService;
import com.aos.repo.dto.BalanceFlowOperateDTO;
import com.aos.repo.dto.BalanceFlowQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BalanceFlowApplication implements IBalanceFlowApplication {
    @Autowired
    private IBalanceFlowService balanceFlowService;

    @Override
    public BaseResponse add(BalanceFlowOperateDTO form) {
        return new BaseResponse(balanceFlowService.add(form));
    }

    @Override
    public BaseResponse query(BalanceFlowQueryDTO form, Pageable page) {
        return new PageResponse(balanceFlowService.query(form,page));
    }

    @Override
    public BaseResponse get(Integer id) {
        return new DataResponse<>(balanceFlowService.get(id));
    }

    @Override
    public BaseResponse update(Integer id, BalanceFlowOperateDTO form) {
        return new BaseResponse(balanceFlowService.update(id,form));
    }

    @Override
    public BaseResponse delete(Integer id) {
        return new BaseResponse(balanceFlowService.delete(id));
    }

    @Override
    public BaseResponse statistic(BalanceFlowQueryDTO form) {
        return new DataResponse<>(balanceFlowService.statistics(form));
    }

    @Override
    public BaseResponse confirm(Integer id) {
        return new BaseResponse(balanceFlowService.confirm(id));
    }
}
