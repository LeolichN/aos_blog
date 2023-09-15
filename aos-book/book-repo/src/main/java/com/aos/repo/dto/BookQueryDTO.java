package com.aos.repo.dto;

import com.aos.repo.entity.Group;
import com.aos.repo.entity.QBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookQueryDTO {
  private Boolean enable;
  private String name;
  private Integer keep;

  public Predicate buildPredicate(Group group) {
    QBook book = QBook.book;
    BooleanBuilder expression = new BooleanBuilder(book.group.eq(group));
    if (enable != null) {
      expression.and(book.enable.eq(enable));
    }
    if (name != null) {
      expression.and(book.name.contains(name));
    }
    return expression;
  }
}
