package com.aos.application.service.impl;

import com.aos.application.service.IUserApplication;
import com.aos.core.response.DataResponse;
import com.aos.domain.service.UserService;
import com.aos.repo.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserApplication implements IUserApplication {

  @Autowired private UserService userService;

  @Override
  public DataResponse<LoginDTO> login(LoginDTO loginDto) {
    return new DataResponse<>(userService.login(loginDto));
  }
}
