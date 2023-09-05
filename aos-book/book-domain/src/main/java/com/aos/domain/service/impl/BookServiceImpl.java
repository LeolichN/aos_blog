package com.aos.domain.service.impl;

import com.aos.domain.service.BookService;
import com.aos.repo.dto.BookAddDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepository;

    public Book addBook(BookAddDTO bookAddDTO) {
        Book book = new Book();
        book.setName(bookAddDTO.getName());
        book.setNotes(bookAddDTO.getNotes());
        book.setPreviewUrl(bookAddDTO.getPreviewUrl());
        book.setVisible(false);
        bookRepository.save(book);
        return book;
    }
}
