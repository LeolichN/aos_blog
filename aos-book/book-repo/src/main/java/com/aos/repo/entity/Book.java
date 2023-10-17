package com.aos.repo.entity;

import com.aos.core.entity.IdAndNameEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "t_book")
public class Book extends IdAndNameEntity {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//  @JoinTable(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  @NotNull
  private Group group;

  @Column(length = 1024)
  private String notes;

  @Column(nullable = false)
  private Boolean enable = true;

  @ManyToOne(optional = true, fetch = FetchType.EAGER)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Account defaultExpenseAccount;

  @ManyToOne(optional = true, fetch = FetchType.EAGER)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Account defaultIncomeAccount;

  @ManyToOne(optional = true, fetch = FetchType.EAGER)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Account defaultTransferFromAccount;

  @ManyToOne(optional = true, fetch = FetchType.EAGER)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Account defaultTransferToAccount;

  @OneToOne(optional = true, fetch = FetchType.EAGER)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Category defaultExpenseCategory;

  @OneToOne(optional = true, fetch = FetchType.EAGER)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Category defaultIncomeCategory;

  @Column(nullable = false, length = 8)
  @NotNull
  private String defaultCurrencyCode;

  private Long exportAt;

}
