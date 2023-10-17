package com.aos.repo.vo;

import com.aos.repo.entity.Category;
import com.aos.repo.entity.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookVO {
  private Integer id;
  private String name;
  private String notes;
  private Boolean enable;
  private String defaultCurrencyCode;
  private AccountVO defaultExpenseAccount;
  private AccountVO defaultIncomeAccount;
  private AccountVO defaultTransferFromAccount;
  private AccountVO defaultTransferToAccount;
  private CategoryVO defaultExpenseCategory;
  private CategoryVO defaultIncomeCategory;
  private boolean isDefault;
}
