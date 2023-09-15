package com.aos.application.service;

import com.aos.core.response.DataResponse;
import com.aos.repo.dto.BookAddDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookApplication {
  DataResponse<Book> addBook(BookAddDTO bookAddDTO);

  DataResponse<BookVO> getBook(Integer id);

  DataResponse<Page<BookVO>> queryBookList(BookQueryDTO bookQueryDTO, Pageable page);
}
