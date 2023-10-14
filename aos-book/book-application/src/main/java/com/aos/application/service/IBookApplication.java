package com.aos.application.service;

import com.aos.core.response.BaseResponse;
import com.aos.core.response.DataResponse;
import com.aos.core.response.PageResponse;
import com.aos.repo.dto.BookOperateDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import org.springframework.data.domain.Pageable;

public interface IBookApplication {
  BaseResponse addBook(BookOperateDTO bookAddDTO);

  DataResponse<BookVO> getBook(Integer id);

  PageResponse queryBookList(BookQueryDTO bookQueryDTO, Pageable page);

  BaseResponse all(BookQueryDTO queryDTO);

  BaseResponse update(Integer id, BookOperateDTO operateDTO);

  BaseResponse delete(Integer id);

  BaseResponse toggle(Integer id);
}
