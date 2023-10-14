package com.aos.interfaces.service;

import com.aos.core.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface IGlobalConfigInterface {

    @GetMapping("/version")
    BaseResponse handleVersion();

}
