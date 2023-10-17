package com.aos.repo.mapper;

import com.aos.repo.dto.AccountOperateDTO;
import com.aos.repo.entity.Account;
import com.aos.repo.vo.AccountVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "convertedBalance",ignore = true)
    @Mapping(target = "typeName",ignore = true)
    @Mapping(target = "asOfDate",ignore = true)
    AccountVO toAccountVO(Account account);

    @Mapping(target = "initialBalance",source = "balance")
    @Mapping(target = "balance",source = "balance")
    @Mapping(target = "enable",constant = "true")
    @Mapping(target = "group",ignore = true)
    @Mapping(target = "deleted",ignore = true)
    Account toAccount(AccountOperateDTO accountAddDTO);
}
