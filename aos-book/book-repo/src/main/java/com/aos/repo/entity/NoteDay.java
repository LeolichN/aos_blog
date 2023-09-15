package com.aos.repo.entity;

import com.aos.core.entity.BaseEntity;
import com.aos.core.validation.TitleField;
import com.aos.repo.validation.NotesField;
import com.aos.repo.validation.TimeField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_user_note_day")
@Getter
@Setter
public class NoteDay extends BaseEntity {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @NotNull
  private User user;

  @Column(length = 16, nullable = false)
  @NotNull
  @TitleField
  private String title;

  @Column(length = 1024)
  @NotesField
  private String notes; // 备注

  @Column(nullable = false)
  @NotNull
  @TimeField
  private Long startDate; // 起始日期

  @Column @NotNull @TimeField private Long endDate;

  @Column @TimeField private Long nextDate;

  @Column @NotNull private Integer repeatType;

  @Column(name = "c_interval")
  private Integer interval; // 间隔

  @NotNull private Integer totalCount;

  @NotNull private Integer runCount;
}
