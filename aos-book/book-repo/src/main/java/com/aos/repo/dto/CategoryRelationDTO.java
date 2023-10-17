package com.aos.repo.dto;

import com.aos.core.validation.NotZero;
import com.aos.repo.entity.CategoryRelation;
import com.aos.repo.validation.AmountField;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryRelationDTO {

    @NotNull
    private Integer categoryId;

    @NotNull
    @AmountField
    @NotZero
    private BigDecimal amount;

    @AmountField
    @NotZero
    private BigDecimal convertedAmount;

    public boolean equals(CategoryRelation categoryRelation) {
        if (!categoryRelation.getCategory().getId().equals(categoryId)) {
            return false;
        }
        if (categoryRelation.getAmount().compareTo(amount) != 0) {
            return false;
        }
        if (categoryRelation.getConvertedAmount().compareTo(convertedAmount) != 0) {
            return false;
        }
        return true;
    }

}
