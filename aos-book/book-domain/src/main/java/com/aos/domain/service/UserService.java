package com.aos.domain.service;

import com.aos.repo.dto.LoginDTO;

public interface UserService {
  LoginDTO login(LoginDTO loginDTO);
}
