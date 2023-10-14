package com.aos.application.service.impl;

import com.aos.application.service.IUserApplication;
import com.aos.core.response.BaseResponse;
import com.aos.domain.service.UserService;
import com.aos.infrustructure.shared.InitialStateService;
import com.aos.repo.dto.LoginDTO;
import com.aos.core.response.DataResponse;
import com.aos.repo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserApplication implements IUserApplication {

  @Autowired private UserService userService;
  @Autowired private InitialStateService initialStateService;

  @Override
  public DataResponse<LoginDTO> login(LoginDTO loginDto) {
    return new DataResponse<>(userService.login(loginDto));
  }

  @Override
  public BaseResponse handleInitState() {
    return new DataResponse<>(initialStateService.handleInitState());
  }
}
