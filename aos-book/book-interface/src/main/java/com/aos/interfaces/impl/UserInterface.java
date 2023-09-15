package com.aos.interfaces.impl;

import com.aos.application.service.IUserApplication;
import com.aos.core.response.DataResponse;
import com.aos.interfaces.IUserInterface;
import com.aos.repo.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInterface implements IUserInterface {

  @Autowired private IUserApplication userApplication;

  @Override
  public DataResponse<LoginDTO> login(LoginDTO loginDto) {
    return userApplication.login(loginDto);
  }
}
