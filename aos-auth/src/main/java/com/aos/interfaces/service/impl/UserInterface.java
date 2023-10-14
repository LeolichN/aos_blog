package com.aos.interfaces.service.impl;

import com.aos.application.service.IUserApplication;
import com.aos.core.response.BaseResponse;
import com.aos.interfaces.service.IUserInterface;
import com.aos.repo.dto.LoginDTO;
import com.aos.core.response.DataResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInterface implements IUserInterface {

  @Autowired private IUserApplication userApplication;

  @Override
  public DataResponse<LoginDTO> login(LoginDTO loginDto) {
    return userApplication.login(loginDto);
  }

  @Override
  public BaseResponse handleInitState() {
    return userApplication.handleInitState();
  }
}
