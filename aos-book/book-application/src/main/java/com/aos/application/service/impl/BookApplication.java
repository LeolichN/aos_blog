package com.aos.application.service.impl;

import com.aos.application.service.IBookApplication;
import com.aos.core.response.DataResponse;
import com.aos.domain.service.BookService;
import com.aos.repo.dto.BookAddDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BookApplication implements IBookApplication {

  @Autowired private BookService bookService;

  @Override
  public DataResponse<Book> addBook(BookAddDTO bookAddDTO) {
    Book book = bookService.addBook(bookAddDTO);
    return new DataResponse<>(book);
  }

  @Override
  public DataResponse<BookVO> getBook(Integer id) {
    return new DataResponse<>(bookService.getBook(id));
  }

  @Override
  public DataResponse<Page<BookVO>> queryBookList(BookQueryDTO bookQueryDTO, Pageable page) {
    return new DataResponse<>(bookService.queryBookList(bookQueryDTO, page));
  }
}
