package com.aos.repo.entity;

import com.aos.repo.enums.CategoryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_category")
@Getter
@Setter
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Book book;

  @Column(length = 64, nullable = false)
  private String name;

  @Column(length = 4096)
  private String notes;

  @Column(nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private CategoryType type;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  private Category parent;
}
