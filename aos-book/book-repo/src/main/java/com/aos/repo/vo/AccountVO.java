package com.aos.repo.vo;

import com.aos.repo.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountVO {


    private Integer id;
    private String name;
    private AccountType type;
    private String typeName;
    private String no;
    private BigDecimal balance;
    private BigDecimal convertedBalance;
    private Boolean enable;
    private Boolean include;
    private Boolean canExpense;
    private Boolean canIncome;
    private Boolean canTransferFrom;
    private Boolean canTransferTo;
    private String notes;
    private String currencyCode;

    private BigDecimal creditLimit;
    private Integer billDay;

    private BigDecimal apr;
    private Long asOfDate;

    public BigDecimal getRemainLimit() {
        if (creditLimit == null) return null;
        return creditLimit.add(getBalance());
    }

}
