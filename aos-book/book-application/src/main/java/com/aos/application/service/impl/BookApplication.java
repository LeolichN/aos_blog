package com.aos.application.service.impl;

import com.aos.domain.service.BookService;
import com.aos.repo.dto.BookAddDTO;
import com.aos.application.service.IBookApplication;
import com.aos.core.response.DataResponse;
import com.aos.repo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookApplication implements IBookApplication {

    @Autowired
    private BookService bookService;

    @Override
    public DataResponse<Book> addBook(BookAddDTO bookAddDTO) {
        Book book = bookService.addBook(bookAddDTO);
        return new DataResponse<>(book);
    }
}
