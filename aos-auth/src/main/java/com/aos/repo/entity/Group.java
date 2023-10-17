package com.aos.repo.entity;

import com.aos.core.entity.IdAndNameEntity;
import com.aos.core.validation.NotesField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_group")
@Getter
@Setter
public class Group extends IdAndNameEntity {

  @Column(length = 1024)
  @NotesField
  private String notes;

  @Column(nullable = false)
  @NotNull
  private Boolean enable = true;

  @OneToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private User creator;

  @OneToMany(
      mappedBy = "group",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private Set<UserGroupRelation> relations = new HashSet<>();

  @Column(nullable = false, length = 8)
  @NotNull
  private String defaultCurrencyCode;

  @Column(nullable = false)
  @NotNull
  private Boolean isDefault = false;

  public Group() {}

  public Group(Integer id) {
    super.setId(id);
  }
}
