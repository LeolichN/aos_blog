package com.aos.repo.repository;

import java.util.Optional;

import com.aos.core.repository.BaseRepository;
import com.aos.repo.entity.Book;
import com.aos.repo.entity.Payee;
import org.springframework.stereotype.Repository;

@Repository
public interface PayeeRepo extends BaseRepository<Payee> {

    boolean existsByBookAndName(Book book, String name);

    long countByBook(Book book);

    Optional<Payee> findOneByBookAndId(Book book, Integer id);

    void deleteByBook(Book book);

}
