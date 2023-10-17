package com.aos.repo.entity;

import com.aos.core.entity.IdAndNameEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_tag")
@Setter
@Getter
public class Tag extends IdAndNameEntity {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Book book;

  @Column(length = 1024)
  private String notes;

  @Column(nullable = false)
  private Boolean enable = true;

  @Column(nullable = false)
  private Integer level;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Tag parent;

  @Column(nullable = false)
  private Boolean canExpense;

  @Column(nullable = false)
  private Boolean canIncome;

  @Column(nullable = false)
  private Boolean canTransfer;
}
