package com.aos.interfaces.impl;

import com.aos.application.service.IBookApplication;
import com.aos.core.response.BaseResponse;
import com.aos.core.response.DataResponse;
import com.aos.core.response.PageResponse;
import com.aos.interfaces.IBookInterface;
import com.aos.repo.dto.BookOperateDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Tag(name = "账本")
public class BookInterface implements IBookInterface {
  @Autowired private IBookApplication bookApplication;

  @Override
  public BaseResponse handleAdd(BookOperateDTO bookOperateDTO) {
    return bookApplication.addBook(bookOperateDTO);
  }

  @Override
  public DataResponse<BookVO> handleGet(Integer id) {
    return bookApplication.getBook(id);
  }

  @Override
  public PageResponse queryBookList(BookQueryDTO bookQueryDTO, Pageable page) {
    return bookApplication.queryBookList(bookQueryDTO, page);
  }

  @Override
  public BaseResponse handleAll(BookQueryDTO queryDTO) {
    return bookApplication.all(queryDTO);
  }

  @Override
  public BaseResponse handleUpdate(Integer id, BookOperateDTO operateDTO) {
    return bookApplication.update(id,operateDTO);
  }

  @Override
  public BaseResponse handleDelete(Integer id) {
    return bookApplication.delete(id);
  }

  @Override
  public BaseResponse handleToggle(Integer id) {
    return bookApplication.toggle(id);
  }
}
