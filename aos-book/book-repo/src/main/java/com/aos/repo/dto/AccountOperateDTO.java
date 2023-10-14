package com.aos.repo.dto;

import com.aos.core.validation.NameField;
import com.aos.core.validation.NotesField;
import com.aos.repo.enums.AccountType;
import com.aos.repo.validation.AccountCurrencyCodeField;
import com.aos.repo.validation.AccountNoField;
import com.aos.repo.validation.AmountField;
import com.aos.repo.validation.BillDayField;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountOperateDTO {

    private Integer id;

    private AccountType type;

    @NotBlank
    @NameField
    private String name;

    private String no;

    private BigDecimal balance;

    private Boolean include;

    private Boolean canTransferFrom;

    private Boolean canTransferTo;

    private Boolean canExpense;

    private Boolean canIncome;

    private String notes;

    private String currencyCode;

    private BigDecimal creditLimit;

    private Integer billDay;

    private BigDecimal apr;

}
