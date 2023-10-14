package com.aos.interfaces.service;

import com.aos.core.response.BaseResponse;
import com.aos.repo.dto.LoginDTO;
import com.aos.core.response.DataResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public interface IUserInterface {

  @PostMapping("/login")
  DataResponse<LoginDTO> login(@Valid @RequestBody LoginDTO loginDto);

  @GetMapping("/initState")
  BaseResponse handleInitState();
}
