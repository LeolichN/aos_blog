package com.aos.repo.repository;

import java.util.List;

import com.aos.core.repository.BaseRepository;
import com.aos.repo.entity.Account;
import com.aos.repo.entity.Group;
import com.aos.repo.enums.AccountType;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends BaseRepository<Account> {

    boolean existsByGroupAndName(Group group, String name);

    int countByGroup(Group group);

    List<Account> findAllByGroupAndTypeAndEnableAndInclude(Group group, AccountType type, Boolean enable, Boolean include);

}
