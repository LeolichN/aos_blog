package com.aos.domain.service.impl;

import com.aos.domain.service.UserService;
import com.aos.infrustructure.config.UserPO;
import com.aos.infrustructure.shared.InitialStateService;
import com.aos.repo.dto.LoginDTO;
import com.aos.repo.entity.InitStateDTO;
import com.aos.repo.entity.User;
import com.aos.repo.repository.UserRepo;
import com.aos.core.exception.FailureMessageException;
import com.aos.core.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

  @Autowired private UserRepo userRepo;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public LoginDTO login(LoginDTO loginDTO) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
    Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    UserPO user = ((UserPO)authenticate.getPrincipal());
    if (user == null) {
      throw new FailureMessageException("user.login.wrong.credentials");
    }
    String jwt = JWTUtils.createToken(user.getUserId(),user.getUsername(), 72);
    loginDTO.setAccessToken(jwt);
    loginDTO.setUsername(user.getUsername());
    loginDTO.setRemember(loginDTO.getRemember());
    return loginDTO;
  }

  @Override
  public User getUser(Integer userId) {
    return userRepo.findById(userId).orElse(null);
  }

  @Override
  public User getCurrentUser() {
    Integer bizId = JWTUtils.getBizId();
    return getUser(bizId);
  }
}
