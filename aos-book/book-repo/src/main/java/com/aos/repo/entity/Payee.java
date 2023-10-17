package com.aos.repo.entity;

import com.aos.core.entity.IdAndNameEntity;
import com.aos.core.validation.NameField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_payee")
@Getter
@Setter
public class Payee extends IdAndNameEntity {


  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  @NotNull
  private Book book;

  @Column(length = 4096)
  private String notes;

  @Column(nullable = false)
  private Boolean enable = true;

  @Column(nullable = false)
  private Boolean canExpense;

  @Column(nullable = false)
  private Boolean canIncome;
}
