package com.aos.repo.vo;

import com.aos.repo.enums.CategoryType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CategoryVO {

    private CategoryType type;
    private String notes;
    private Boolean enable;
    private Boolean canExpense;
    private Boolean canIncome;

    // todo    what if do not set tree-node in backend ?
    private Integer parentId;
    private List<CategoryVO> children;

}
