package com.aos.repo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
  @NotBlank private String username;

  @NotBlank private String password;

  private String accessToken;

  private Boolean remember = false;
}
