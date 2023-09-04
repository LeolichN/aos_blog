package com.aos.repo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_tag")
@Setter@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 4096)
    private String notes;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Tag parent;

    @Column(nullable = false)
    private Boolean canExpense; //是否可支出

    @Column(nullable = false)
    private Boolean canIncome; //是否可收入

    @Column(nullable = false)
    private Boolean canTransfer; //是否可转账
}
