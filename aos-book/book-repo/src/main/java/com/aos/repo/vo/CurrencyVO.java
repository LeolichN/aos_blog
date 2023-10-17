package com.aos.repo.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyVO {
    private String code;
    private String name;
    private String description;
    private Double rate;
}
