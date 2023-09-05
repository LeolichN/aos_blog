package com.aos.interfaces.impl;

import com.aos.repo.dto.BookAddDTO;
import com.aos.application.service.IBookApplication;
import com.aos.core.response.DataResponse;
import com.aos.interfaces.IBookInterface;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Tag(name = "账本")
public class BookInterface implements IBookInterface {
    @Autowired
    private IBookApplication bookApplication;

    @Override
    public DataResponse<Book> handleAdd(BookAddDTO bookAddDTO) {
        return bookApplication.addBook(bookAddDTO);
    }

    @Override
    public DataResponse<Book> handleGet(Integer id) {
        return null;
    }

    @Override
    public DataResponse<Book> handleAdd(BookQueryDTO bookQueryDTO, Pageable page) {
        return null;
    }
}
