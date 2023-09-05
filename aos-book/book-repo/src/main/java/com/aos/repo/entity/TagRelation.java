package com.aos.repo.entity;

import com.aos.core.entity.BaseEntity;
import com.aos.repo.validation.AmountField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "t_user_tag_relation")
@Getter
@Setter
public class TagRelation extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    private Tag tag;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    private BalanceFlow balanceFlow;

    @Column(nullable = false)
    @NotNull
    @AmountField
    private BigDecimal amount;

    @AmountField
    private BigDecimal convertedAmount; // 金额

}
