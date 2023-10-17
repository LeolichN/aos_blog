package com.aos.repo.entity;

import com.aos.core.entity.BaseEntity;
import com.aos.core.validation.TitleField;
import com.aos.repo.enums.FlowType;
import com.aos.repo.validation.AmountField;
import com.aos.core.validation.NotesField;
import com.aos.core.validation.TimeField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_balance_flow")
@Getter
@Setter
public class BalanceFlow extends BaseEntity {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  @NotNull
  private Book book;

  @Column(nullable = false)
  @Enumerated(EnumType.ORDINAL)
  @NotNull
  private FlowType type;

  @Column(nullable = false)
  @NotNull
  @AmountField
  private BigDecimal amount;

  @AmountField private BigDecimal convertedAmount;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Account account;

  @Column(nullable = false)
  @NotNull
  @TimeField
  private Long createTime;

  @Column(length = 32)
  @TitleField
  private String title;

  @Column(length = 1024)
  @NotesField
  private String notes; // 备注

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  @NotNull
  private User creator;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  @NotNull
  private Group group;

  @OneToMany(
      mappedBy = "balanceFlow",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private Set<TagRelation> tags = new HashSet<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Account to;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Payee payee;

  @Column(nullable = false)
  @NotNull
  private Boolean confirm;

  private Boolean include;

  @OneToMany(
      mappedBy = "balanceFlow",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  //    @NotEmpty
  private Set<CategoryRelation> categories = new HashSet<>();

  @Column(nullable = false, updatable = false)
  private Long insertAt = System.currentTimeMillis();
}
