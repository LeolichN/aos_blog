package com.aos.interfaces;

import com.aos.core.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/currencies")
@RestController
public interface ICurrencyInterface {

    @GetMapping("/all")
    BaseResponse handleAll();
}
