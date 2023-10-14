package com.aos.domain.service;

import com.aos.repo.dto.BookOperateDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
  boolean addBook(BookOperateDTO bookAddDTO);

  BookVO getBookVO(Integer id);

  Book getBook(Integer id);

  Page<BookVO> queryBookList(BookQueryDTO bookQueryDTO, Pageable page);

  Object queryAll(BookQueryDTO queryDTO);

  boolean update(Integer id, BookOperateDTO operateDTO);

  boolean delete(Integer id);

  boolean toggle(Integer id);
}
