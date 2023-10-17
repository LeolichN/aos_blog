package com.aos.repo.entity;

import com.aos.core.entity.IdAndNameEntity;
import com.aos.repo.enums.CategoryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_category")
@Getter
@Setter
public class Category extends IdAndNameEntity {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Book book;

  @Column(length = 4096)
  private String notes;

  @Column(nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private CategoryType type;

  @Column(nullable = false)
  private Integer level;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Category parent;
}
