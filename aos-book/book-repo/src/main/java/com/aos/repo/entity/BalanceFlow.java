package com.aos.repo.entity;

import com.aos.core.entity.BaseEntity;
import com.aos.repo.enums.FlowType;
import com.aos.repo.validation.AmountField;
import com.aos.repo.validation.NotesField;
import com.aos.repo.validation.TimeField;
import com.aos.core.validation.TitleField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_user_balance_flow")
@Getter @Setter
public class BalanceFlow extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
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

    @AmountField
    private BigDecimal convertedAmount;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
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
    private String notes; //备注

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    private User creator;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    private Group group;

    @OneToMany(mappedBy = "balanceFlow", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TagRelation> tags = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Account to;

    @ManyToOne(fetch = FetchType.LAZY)
    private Payee payee;

    @Column(nullable = false)
    @NotNull
    private Boolean confirm;

    private Boolean include;

    @OneToMany(mappedBy = "balanceFlow", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @NotEmpty
    private Set<CategoryRelation> categories = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private Long insertAt = System.currentTimeMillis();

}
