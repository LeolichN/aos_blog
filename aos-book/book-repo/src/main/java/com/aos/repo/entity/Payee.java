package com.aos.repo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_payee")
@Getter
@Setter
public class Payee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @NotNull
  private Book book;

  @Column(length = 64, nullable = false)
  private String name;

  @Column(length = 4096)
  private String notes;

  @Column(nullable = false)
  private Boolean canExpense;

  @Column(nullable = false)
  private Boolean canIncome;
}
