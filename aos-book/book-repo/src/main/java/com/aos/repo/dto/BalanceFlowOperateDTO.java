package com.aos.repo.dto;

import com.aos.core.validation.NotesField;
import com.aos.core.validation.TimeField;
import com.aos.core.validation.TitleField;
import com.aos.repo.enums.FlowType;
import com.aos.repo.validation.AmountField;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BalanceFlowOperateDTO {

    private Integer bookId; // 默认为当前账本

    private FlowType type;

    @TitleField
    private String title;

    @NotNull
    @TimeField
    private Long createTime;

    private Integer accountId;

    private Integer payeeId;

    private Set<Integer> tags;

    @Valid
//    @NotEmpty  //transfer和adjust可以为空
    private List<CategoryRelationDTO> categories;

    private Integer toId;

    // 只有transfer和adjust会传
    @AmountField
    private BigDecimal amount;

    @AmountField
    private BigDecimal convertedAmount;

    @NotesField
    private String notes;

    @NotNull
    private Boolean confirm; // 是否确认

    @NotNull
    private Boolean include; // 是否包含统计

}
