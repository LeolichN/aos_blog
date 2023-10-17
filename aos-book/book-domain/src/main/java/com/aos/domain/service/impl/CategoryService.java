package com.aos.domain.service.impl;

import com.aos.core.exception.ItemNotFoundException;
import com.aos.domain.service.ICategoryService;
import com.aos.repo.entity.Book;
import com.aos.repo.entity.Category;
import com.aos.repo.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public void deleteByBook(Book book) {
        categoryRepo.deleteByBook(book);
    }

    @Override
    public Category findCategoryByBookAndId(Book book, Integer id) {
        return categoryRepo.findById(id).orElseThrow(ItemNotFoundException::new);
    }
}
