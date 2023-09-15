package com.aos.repo.entity;

import com.aos.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "t_user_user_group_relation",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "group_id"})})
@Getter
@Setter
public class UserGroupRelation extends BaseEntity {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @NotNull
  private User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id")
  @NotNull
  private Group group;

  @Column(nullable = false)
  @NotNull
  private Integer role;

  public UserGroupRelation() {}

  public UserGroupRelation(User user, Group group, Integer role) {
    this.user = user;
    this.group = group;
    this.role = role;
  }
}
