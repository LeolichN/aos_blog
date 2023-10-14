package com.aos.repo.repository;

import com.aos.core.repository.BaseRepository;
import com.aos.repo.entity.Account;
import com.aos.repo.entity.Book;
import com.aos.repo.entity.Category;
import com.aos.repo.entity.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends BaseRepository<Book> {
    boolean existsByGroupAndName(Group group, String name);

    boolean existsByDefaultExpenseAccount(Account account);

    boolean existsByDefaultIncomeAccount(Account account);

    boolean existsByDefaultTransferFromAccount(Account account);

    boolean existsByDefaultTransferToAccount(Account account);

    boolean existsByDefaultExpenseCategory(Category category);

    boolean existsByDefaultIncomeCategory(Category category);

    int countByGroup(Group group);

    List<Book> findAllByGroup(Group group);
}
