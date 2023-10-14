package com.aos.application.service.impl;

import com.aos.application.service.IBookApplication;
import com.aos.core.response.BaseResponse;
import com.aos.core.response.DataResponse;
import com.aos.core.response.PageResponse;
import com.aos.domain.service.BookService;
import com.aos.repo.dto.BookOperateDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BookApplication implements IBookApplication {

  @Autowired private BookService bookService;

  @Override
  public BaseResponse addBook(BookOperateDTO bookAddDTO) {
    return new BaseResponse( bookService.addBook(bookAddDTO));
  }

  @Override
  public DataResponse<BookVO> getBook(Integer id) {
    return new DataResponse<>(bookService.getBookVO(id));
  }

  @Override
  public PageResponse queryBookList(BookQueryDTO bookQueryDTO, Pageable page) {
    return new PageResponse(bookService.queryBookList(bookQueryDTO, page));
  }

  @Override
  public BaseResponse all(BookQueryDTO queryDTO) {
    return new DataResponse(bookService.queryAll(queryDTO));
  }

  @Override
  public BaseResponse update(Integer id, BookOperateDTO operateDTO) {
    return new BaseResponse(bookService.update(id,operateDTO));
  }

  @Override
  public BaseResponse delete(Integer id) {
    return new BaseResponse(bookService.delete(id));
  }

  @Override
  public BaseResponse toggle(Integer id) {
    return new BaseResponse(bookService.toggle(id));
  }
}
