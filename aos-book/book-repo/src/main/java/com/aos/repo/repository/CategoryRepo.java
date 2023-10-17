package com.aos.repo.repository;


import java.util.List;
import java.util.Optional;

import com.aos.core.repository.BaseRepository;
import com.aos.repo.entity.Book;
import com.aos.repo.entity.Category;
import com.aos.repo.enums.CategoryType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepo extends BaseRepository<Category> {

    Optional<Category> findOneByBookAndId(Book book, Integer id);

    long countByBook(Book book);

    boolean existsByBookAndParentAndTypeAndName(Book book, Category parent, CategoryType type, String name);

    List<Category> findAllByBookAndType(Book book, CategoryType type);

//    @Modifying
//    @Query("delete from Category where book = :book")
    int deleteByBook(Book book);

    @Modifying
    @Transactional
    @Query("UPDATE Category SET parent = NULL where parent = :category")
    void unChildren(Category category);

}
