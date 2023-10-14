package com.aos.domain.service.impl;

import com.aos.core.exception.FailureMessageException;
import com.aos.core.exception.ItemExistsException;
import com.aos.domain.service.BookService;
import com.aos.repo.dto.BookOperateDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.entity.Group;
import com.aos.repo.enums.Limitation;
import com.aos.repo.mapper.BookVOMapper;
import com.aos.repo.repository.BookRepo;
import com.aos.repo.vo.BookVO;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BookServiceImpl implements BookService {
  @Autowired private BookRepo bookRepository;
  @Autowired private GroupService groupService;

  public boolean addBook(BookOperateDTO bookAddDTO) {
    Group group = groupService.getCurrentUserDefaultGroup();
    if (bookRepository.countByGroup(group) >= Limitation.book_max_count) {
      throw new FailureMessageException("book.max.count");
    }
    if (bookRepository.existsByGroupAndName(group, bookAddDTO.getName())) {
      throw new ItemExistsException();
    }
//    currencyService.checkCode(form.getDefaultCurrencyCode());
    bookRepository.save(BookVOMapper.MAPPER.toBook(bookAddDTO));
    return true;
  }

  @Override
  public BookVO getBookVO(Integer id) {
    Optional<Book> book = bookRepository.findById(id);
    return BookVOMapper.MAPPER.toBookVO(book.get());
  }

  @Override
  public Book getBook(Integer id) {
    return bookRepository.findById(id).orElse(null);
  }

  @Override
  public Page<BookVO> queryBookList(BookQueryDTO bookQueryDTO, Pageable page) {
    // todo get current user group
    Page<Book> entityPage = bookRepository.findAll(bookQueryDTO.buildPredicate(null), page);
    return entityPage.map(book -> BookVOMapper.MAPPER.toBookVO(book));
  }

  @Override
  public Object queryAll(BookQueryDTO queryDTO) {
    queryDTO.setEnable(true);
    Group group = groupService.getCurrentUserDefaultGroup();
    List<Book> entityList = bookRepository.findAll(queryDTO.buildPredicate(group));

    return Collections.EMPTY_LIST;// entityList.stream().map(BookMapper::toDetails).toList();
  }

  @Override
  public boolean update(Integer id, BookOperateDTO operateDTO) {
    Group group = groupService.getCurrentUserDefaultGroup();
    Book entity = bookRepository.findById(id).orElseThrow();
    if (!Objects.equals(entity.getName(), operateDTO.getName())) {
      if (StringUtils.hasText(operateDTO.getName())) {
        if (bookRepository.existsByGroupAndName(group, operateDTO.getName())) {
          throw new ItemExistsException();
        }
      }
    }
    bookMapper.updateEntity(operateDTO, entity);
    bookRepository.save(entity);
    return true;
  }

  @Override
  public boolean delete(Integer id) {
    // 默认的账本不能操作，前端按钮禁用
    Book entity = bookRepository.findById(id).orElseThrow();
    if (balanceFlowRepository.existsByBook(entity)) {
      throw new FailureMessageException("book.delete.has.flow");
    }
    categoryRepository.deleteByBook(entity);
    payeeRepository.deleteByBook(entity);
    tagRepository.deleteByBook(entity);
    bookRepository.delete(entity);
    return true;
  }

  @Override
  public boolean toggle(Integer id) {
    Book entity = bookRepository.findById(id).orElseThrow();
    entity.setEnable(!entity.getEnable());
    bookRepository.save(entity);
    return true;
  }
}
