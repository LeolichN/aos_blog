package com.aos.repo.dto;

import com.aos.core.validation.NameField;
import com.aos.core.validation.NotesField;
import com.aos.repo.validation.AccountCurrencyCodeField;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookOperateDTO {
  @NotBlank
  @NameField
  private String name;

  @AccountCurrencyCodeField
  private String defaultCurrencyCode;

  private Integer defaultExpenseAccountId;
  private Integer defaultIncomeAccountId;
  private Integer defaultTransferFromAccountId;
  private Integer defaultTransferToAccountId;
  private Integer defaultExpenseCategoryId;
  private Integer defaultIncomeCategoryId;

  @NotesField
  private String notes;
}
