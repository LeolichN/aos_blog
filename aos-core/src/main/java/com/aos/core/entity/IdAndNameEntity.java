package com.aos.core.entity;

import com.aos.core.validation.NameField;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class IdAndNameEntity extends BaseEntity {

    @Column(length = 64, nullable = false)
    @NameField
    private String name;

}
