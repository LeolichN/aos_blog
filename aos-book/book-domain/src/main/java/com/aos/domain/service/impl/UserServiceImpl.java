package com.aos.domain.service.impl;

import com.aos.core.security.JWTUtils;
import com.aos.domain.service.UserService;
import com.aos.repo.dto.LoginDTO;
import com.aos.repo.entity.User;
import com.aos.repo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

  @Autowired private UserRepo userRepo;

  @Override
  public LoginDTO login(LoginDTO loginDTO) {
    LoginDTO response = new LoginDTO();
    User user = userRepo.findOneByUsername(loginDTO.getUsername()).get();
    String jwt = JWTUtils.createToken(user.getId().toString(), 72);
    response.setAccessToken(jwt);
    response.setUsername(user.getUsername());
    response.setRemember(loginDTO.getRemember());
    return response;
  }
}
