package com.aos.domain.service.impl;

import com.aos.domain.service.BookService;
import com.aos.repo.dto.BookAddDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.mapper.BookVOMapper;
import com.aos.repo.repository.BookRepo;
import com.aos.repo.vo.BookVO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {
  @Autowired private BookRepo bookRepository;

  public Book addBook(BookAddDTO bookAddDTO) {
    Book book = new Book();
    book.setName(bookAddDTO.getName());
    book.setNotes(bookAddDTO.getNotes());
    book.setPreviewUrl(bookAddDTO.getPreviewUrl());
    book.setVisible(false);
    bookRepository.save(book);
    return book;
  }

  @Override
  public BookVO getBook(Integer id) {
    Optional<Book> book = bookRepository.findById(id);
    return BookVOMapper.MAPPER.toBookVO(book.get());
  }

  @Override
  public Page<BookVO> queryBookList(BookQueryDTO bookQueryDTO, Pageable page) {
    // todo get current user group
    Page<Book> entityPage = bookRepository.findAll(bookQueryDTO.buildPredicate(null), page);
    return entityPage.map(book -> BookVOMapper.MAPPER.toBookVO(book));
  }
}
