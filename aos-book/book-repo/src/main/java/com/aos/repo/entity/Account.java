package com.aos.repo.entity;

import com.aos.core.entity.IdAndNameEntity;
import com.aos.core.validation.NotesField;
import com.aos.repo.enums.AccountType;
import com.aos.repo.validation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_user_account")
@Getter
@Setter
public class Account extends IdAndNameEntity {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id")
  @NotNull
  private Group group;

  @Column(nullable = false)
  @Enumerated(EnumType.ORDINAL)
  @NotNull
  private AccountType type;

  @Column(length = 1024)
  @NotesField
  private String notes;

  @Column(nullable = false)
  @NotNull
  private Boolean enable;

  @Column(length = 32)
  @AccountNoField
  private String no;

  @Column(nullable = false)
  @NotNull
  @AmountField
  private BigDecimal balance;

  @Column(nullable = false)
  @NotNull
  private Boolean include;

  @Column(nullable = false)
  @NotNull
  private Boolean canExpense;

  @Column(nullable = false)
  @NotNull
  private Boolean canIncome;

  @Column(nullable = false)
  @NotNull
  private Boolean canTransferFrom;

  @Column(nullable = false)
  @NotNull
  private Boolean canTransferTo;

  @Column(nullable = false, length = 8)
  @NotBlank
  @AccountCurrencyCodeField
  private String currencyCode;

  @AmountField private BigDecimal initialBalance;

  @AmountField private BigDecimal creditLimit;

  @BillDayField private Integer billDay;

  @Digits(integer = 4, fraction = 4)
  private BigDecimal apr;

  @Column(name = "deleted", columnDefinition = "bit(1) default 0")
  private Boolean deleted = false;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return Objects.equals(getId(), account.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
