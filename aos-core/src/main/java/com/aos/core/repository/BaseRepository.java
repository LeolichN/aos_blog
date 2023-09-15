package com.aos.core.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer>, QuerydslPredicateExecutor<T> {
  @Override
  List<T> findAll(Predicate predicate);

  @Override
  List<T> findAll(Predicate predicate, OrderSpecifier<?>... orders);
}
