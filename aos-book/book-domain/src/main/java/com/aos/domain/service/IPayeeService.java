package com.aos.domain.service;

import com.aos.repo.entity.Book;

public interface IPayeeService {
    void deleteByBook(Book book);
}
