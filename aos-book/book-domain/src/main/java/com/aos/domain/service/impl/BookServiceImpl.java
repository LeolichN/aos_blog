package com.aos.domain.service.impl;

import com.aos.core.exception.FailureMessageException;
import com.aos.core.exception.ItemExistsException;
import com.aos.domain.service.*;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BookServiceImpl implements BookService {
  @Autowired private BookRepo bookRepo;
  @Autowired private IGroupService groupService;
  @Autowired private IBalanceFlowService balanceFlowService;
  @Autowired private ICategoryService categoryService;
  @Autowired private IPayeeService payeeService;
  @Autowired private ITagService tagService;
  @Autowired @Lazy private IAccountService accountService;

  public boolean addBook(BookOperateDTO bookAddDTO) {
    Group group = groupService.getCurrentUserDefaultGroup();
    if (bookRepo.countByGroup(group) >= Limitation.book_max_count) {
      throw new FailureMessageException("book.max.count");
    }
    if (bookRepo.existsByGroupAndName(group, bookAddDTO.getName())) {
      throw new ItemExistsException();
    }
//    currencyService.checkCode(form.getDefaultCurrencyCode());
    Book book = BookVOMapper.MAPPER.toBook(bookAddDTO);
    book.setGroup(group);
    book.setEnable(true);
    bookRepo.save(book);
    return true;
  }

  @Override
  public BookVO getBookVO(Integer id) {
    Optional<Book> book = bookRepo.findById(id);
    return BookVOMapper.MAPPER.toBookVO(book.get());
  }

  @Override
  public Book getBook(Integer id) {
    return bookRepo.findById(id).orElse(null);
  }

  @Override
  public Page<BookVO> queryBookList(BookQueryDTO bookQueryDTO, Pageable page) {
    Page<Book> entityPage = bookRepo.findAll(bookQueryDTO.buildPredicate(null), page);
    return entityPage.map(book -> BookVOMapper.MAPPER.toBookVO(book));
  }

  @Override
  public Object queryAll(BookQueryDTO queryDTO) {
    queryDTO.setEnable(true);
    Group group = groupService.getCurrentUserDefaultGroup();
    List<Book> entityList = bookRepo.findAll(queryDTO.buildPredicate(group));

    return Collections.EMPTY_LIST;// entityList.stream().map(BookMapper::toDetails).toList();
  }

  @Override
  public boolean update(Integer id, BookOperateDTO operateDTO) {
    Group group = groupService.getCurrentUserDefaultGroup();
    Book entity = bookRepo.findById(id).orElseThrow();
    if (!Objects.equals(entity.getName(), operateDTO.getName())) {
      if (StringUtils.hasText(operateDTO.getName())) {
        if (bookRepo.existsByGroupAndName(group, operateDTO.getName())) {
          throw new ItemExistsException();
        }
      }
    }
    updateEntity(operateDTO, entity);
    bookRepo.save(entity);
    return true;
  }

  private void updateEntity(BookOperateDTO operateDTO, Book book) {
    if (StringUtils.hasText(operateDTO.getName())) {
      book.setName( operateDTO.getName() );
    }
    book.setNotes( operateDTO.getNotes() );

    if(Objects.nonNull(operateDTO.getDefaultExpenseAccountId())) {
      if(!Objects.equals(Optional.ofNullable(book.getDefaultExpenseAccount()).map(i->i.getId()).orElse(null), operateDTO.getDefaultExpenseAccountId())){
        book.setDefaultExpenseAccount(accountService.findAccountById(operateDTO.getDefaultExpenseAccountId()));
      }
    }else {
      book.setDefaultExpenseAccount(null);
    }

    if(Objects.nonNull(operateDTO.getDefaultIncomeAccountId())) {
      if (!Objects.equals(
          Optional.ofNullable(book.getDefaultIncomeAccount()).map(i -> i.getId()).orElse(null),
          operateDTO.getDefaultIncomeAccountId())) {
        book.setDefaultIncomeAccount(
            accountService.findAccountById(operateDTO.getDefaultIncomeAccountId()));
      }
    }else {
      book.setDefaultIncomeAccount(null);
    }

    if(Objects.nonNull(operateDTO.getDefaultTransferFromAccountId())) {
      if (!Objects.equals(
          Optional.ofNullable(book.getDefaultTransferFromAccount())
              .map(i -> i.getId())
              .orElse(null),
          operateDTO.getDefaultTransferFromAccountId())) {
        book.setDefaultTransferFromAccount(
            accountService.findAccountById(operateDTO.getDefaultTransferFromAccountId()));
      }
    }else {
      book.setDefaultTransferFromAccount(null);
    }

    if(Objects.nonNull(operateDTO.getDefaultTransferToAccountId())) {
      if (!Objects.equals(
          Optional.ofNullable(book.getDefaultTransferToAccount()).map(i -> i.getId()).orElse(null),
          operateDTO.getDefaultTransferToAccountId())) {
        book.setDefaultTransferToAccount(
            accountService.findAccountById(operateDTO.getDefaultTransferToAccountId()));
      }
    }else {
      book.setDefaultTransferToAccount(null);
    }

    if(Objects.nonNull(operateDTO.getDefaultExpenseCategoryId())) {
      if (!Objects.equals(
          Optional.ofNullable(book.getDefaultExpenseCategory()).map(i -> i.getId()).orElse(null),
          operateDTO.getDefaultExpenseCategoryId())) {
        book.setDefaultExpenseCategory(
            categoryService.findCategoryByBookAndId(
                book, operateDTO.getDefaultExpenseCategoryId()));
      }
    }else {
      book.setDefaultExpenseCategory(null);
    }

    if(Objects.nonNull(operateDTO.getDefaultIncomeCategoryId())){
      if (!Objects.equals(
          Optional.ofNullable(book.getDefaultIncomeCategory()).map(i -> i.getId()).orElse(null),
          operateDTO.getDefaultIncomeCategoryId())) {
        book.setDefaultIncomeCategory(
            categoryService.findCategoryByBookAndId(book, operateDTO.getDefaultIncomeCategoryId()));
      }
    }else {
      book.setDefaultIncomeCategory(null);
    }
  }


  @Override
  public boolean delete(Integer id) {
    // 默认的账本不能操作，前端按钮禁用
    Book entity = bookRepo.findById(id).orElseThrow();
    if (balanceFlowService.existsByBook(entity)) {
      throw new FailureMessageException("book.delete.has.flow");
    }
    categoryService.deleteByBook(entity);
    payeeService.deleteByBook(entity);
    tagService.deleteByBook(entity);
    bookRepo.delete(entity);
    return true;
  }

  @Override
  public boolean toggle(Integer id) {
    Book entity = bookRepo.findById(id).orElseThrow();
    entity.setEnable(!entity.getEnable());
    bookRepo.save(entity);
    return true;
  }
}
