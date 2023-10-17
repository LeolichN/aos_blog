package com.aos.repo.entity;

import com.aos.core.entity.BaseEntity;
import com.aos.repo.validation.AmountField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_tag_relation")
@Getter
@Setter
public class TagRelation extends BaseEntity {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  @NotNull
  private Tag tag;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  @NotNull
  private BalanceFlow balanceFlow;

  @Column(nullable = false)
  @NotNull
  @AmountField
  private BigDecimal amount;

  @AmountField private BigDecimal convertedAmount; // 金额
}
