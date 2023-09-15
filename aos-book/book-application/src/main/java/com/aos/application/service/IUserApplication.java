package com.aos.application.service;

import com.aos.core.response.DataResponse;
import com.aos.repo.dto.LoginDTO;

public interface IUserApplication {
  DataResponse<LoginDTO> login(LoginDTO loginDto);
}
