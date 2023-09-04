package com.aos.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter@Setter
public class BookAddDTO {
    @NotBlank
    private String name;

    private String notes;

    @URL
    private String previewUrl;
}
