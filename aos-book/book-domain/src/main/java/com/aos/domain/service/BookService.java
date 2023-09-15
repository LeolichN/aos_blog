package com.aos.domain.service;

import com.aos.repo.dto.BookAddDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
  Book addBook(BookAddDTO bookAddDTO);

  BookVO getBook(Integer id);

  Page<BookVO> queryBookList(BookQueryDTO bookQueryDTO, Pageable page);
}
