package com.aos.interfaces.impl;

import com.aos.application.service.IBookApplication;
import com.aos.core.response.DataResponse;
import com.aos.interfaces.IBookInterface;
import com.aos.repo.dto.BookAddDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Tag(name = "账本")
public class BookInterface implements IBookInterface {
  @Autowired private IBookApplication bookApplication;

  @Override
  public DataResponse<Book> handleAdd(BookAddDTO bookAddDTO) {
    return bookApplication.addBook(bookAddDTO);
  }

  @Override
  public DataResponse<BookVO> handleGet(Integer id) {
    return bookApplication.getBook(id);
  }

  @Override
  public DataResponse<Page<BookVO>> queryBookList(BookQueryDTO bookQueryDTO, Pageable page) {
    return bookApplication.queryBookList(bookQueryDTO, page);
  }
}
