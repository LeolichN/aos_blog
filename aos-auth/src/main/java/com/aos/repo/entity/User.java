package com.aos.repo.entity;

import com.aos.repo.validation.UserNameField;
import com.aos.core.entity.BaseEntity;
import com.aos.core.validation.NameField;
import com.aos.core.validation.TimeField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_user_user")
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler", "fieldHandler"})
public class User extends BaseEntity {

  @Column(length = 16, unique = true, nullable = true)
  @UserNameField
  private String username;

  @Column(length = 16)
  @NameField
  private String nickName;

  @Column(length = 64)
  private String password;

  @Column(length = 16)
  @NameField
  private String telephone;

  @Column(length = 32)
  @Email
  private String email;

  @Column(length = 128)
  private String registerIp;

  @Column(nullable = false)
  @NotNull
  private Boolean enable = true;

  @TimeField
  private Long registerTime; // 注册时间

  @Column(length = 32)
  private String openId;

  @Column(length = 32, unique = true)
  private String unionId;

  @Column(length = 256)
  private String headimgurl;

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private Set<UserGroupRelation> relations = new HashSet<>();
}
