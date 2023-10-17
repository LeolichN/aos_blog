package com.aos.repo.mapper;

import com.aos.repo.dto.BalanceFlowOperateDTO;
import com.aos.repo.entity.BalanceFlow;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BalanceFlowMapper {

    BalanceFlowMapper MAPPER = Mappers.getMapper(BalanceFlowMapper.class);


    BalanceFlow toBalanceFlow(BalanceFlowOperateDTO form);
}
