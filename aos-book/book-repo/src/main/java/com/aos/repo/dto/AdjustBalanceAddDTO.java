package com.aos.repo.dto;

import com.aos.core.validation.NotesField;
import com.aos.core.validation.TimeField;
import com.aos.core.validation.TitleField;
import com.aos.repo.validation.AmountField;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdjustBalanceAddDTO {

    @NotNull
    private Integer bookId;

    @NotNull
    @TimeField
    private Long createTime;

    @TitleField
    private String title;

    @NotesField
    private String notes;

    @NotNull
    @AmountField
    private BigDecimal balance;

}
