package com.aos.domain.service;

import com.aos.repo.entity.Book;
import com.aos.repo.entity.Category;

public interface ICategoryService {

    void deleteByBook(Book book);

    Category findCategoryByBookAndId(Book book, Integer defaultExpenseCategoryId);
}
