package com.aos.application.service;

import com.aos.repo.dto.BookAddDTO;
import com.aos.core.response.DataResponse;
import com.aos.repo.entity.Book;


public interface IBookApplication {
    DataResponse<Book> addBook(BookAddDTO bookAddDTO);
}
