package com.aos.application.service;

import com.aos.core.response.BaseResponse;
import com.aos.repo.dto.BalanceFlowOperateDTO;
import com.aos.repo.dto.BalanceFlowQueryDTO;
import org.springframework.data.domain.Pageable;

public interface IBalanceFlowApplication {
    BaseResponse add(BalanceFlowOperateDTO form);

    BaseResponse query(BalanceFlowQueryDTO form, Pageable page);

    BaseResponse get(Integer id);

    BaseResponse update(Integer id, BalanceFlowOperateDTO form);

    BaseResponse delete(Integer id);

    BaseResponse statistic(BalanceFlowQueryDTO form);

    BaseResponse confirm(Integer id);
}
