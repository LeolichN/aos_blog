package com.aos.application.service.impl;

import com.aos.application.service.IGlobalConfigApplication;
import com.aos.core.response.BaseResponse;
import com.aos.core.response.DataResponse;
import org.springframework.stereotype.Component;

@Component
public class GlobalConfigApplication implements IGlobalConfigApplication {

    @Override
    public BaseResponse version() {
        return new DataResponse<>("V1.0.0");
    }
}
