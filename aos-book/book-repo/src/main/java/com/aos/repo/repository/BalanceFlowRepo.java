package com.aos.repo.repository;

import java.util.List;

import com.aos.core.repository.BaseRepository;
import com.aos.repo.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceFlowRepo extends BaseRepository<BalanceFlow> {

    boolean existsByAccountOrTo(Account account, Account to);

    boolean existsByBook(Book book);

    long countByCreatorAndInsertAtBetween(User creator, long start, long end);

    boolean existsByPayee(Payee payee);

    List<BalanceFlow> findAllByBook(Book book);

}
