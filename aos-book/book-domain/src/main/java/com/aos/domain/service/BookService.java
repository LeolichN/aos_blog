package com.aos.domain.service;


import com.aos.repo.dto.BookAddDTO;
import com.aos.repo.entity.Book;

public interface BookService {
    Book addBook(BookAddDTO bookAddDTO);
}
