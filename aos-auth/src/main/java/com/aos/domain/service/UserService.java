package com.aos.domain.service;


import com.aos.repo.dto.LoginDTO;
import com.aos.repo.entity.User;

public interface UserService {
  LoginDTO login(LoginDTO loginDTO);

  User getUser(Integer userId);

  User getCurrentUser();
}
