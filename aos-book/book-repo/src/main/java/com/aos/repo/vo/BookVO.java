package com.aos.repo.vo;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BookVO {
    private String name;
    private String notes;
    private String previewUrl;
    private Boolean visible;
}
