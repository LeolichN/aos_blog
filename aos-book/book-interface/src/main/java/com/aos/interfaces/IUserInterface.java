package com.aos.interfaces;

import com.aos.core.response.DataResponse;
import com.aos.repo.dto.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public interface IUserInterface {

  @PostMapping("/login")
  DataResponse<LoginDTO> login(@Valid @RequestBody LoginDTO loginDto);
}
