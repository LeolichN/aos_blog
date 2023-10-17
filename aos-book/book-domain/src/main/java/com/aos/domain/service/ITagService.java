package com.aos.domain.service;

import com.aos.repo.entity.Book;

public interface ITagService {
    void deleteByBook(Book book);
}
