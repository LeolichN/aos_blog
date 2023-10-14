package com.aos.application.service;

import com.aos.core.response.BaseResponse;
import com.aos.repo.dto.LoginDTO;
import com.aos.core.response.DataResponse;


public interface IUserApplication {
  DataResponse<LoginDTO> login(LoginDTO loginDto);

    BaseResponse handleInitState();
}
