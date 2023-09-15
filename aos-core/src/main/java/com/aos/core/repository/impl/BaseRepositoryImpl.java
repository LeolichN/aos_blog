package com.aos.core.repository.impl;

import com.aos.core.repository.BaseRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;

public class BaseRepositoryImpl<T> extends QuerydslJpaRepository<T, Integer>
    implements BaseRepository<T> {
  private final JPAQueryFactory jpaQueryFactory;

  public BaseRepositoryImpl(
      JpaEntityInformation<T, Integer> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.jpaQueryFactory = new JPAQueryFactory(entityManager);
  }
}
