package com.aos.interfaces.service.impl;

import com.aos.core.response.BaseResponse;
import com.aos.interfaces.service.IGlobalConfigInterface;
import org.springframework.stereotype.Component;

@Component
public class GlobalConfigInterface implements IGlobalConfigInterface {
    @Override
    public BaseResponse handleVersion() {
        return null;
    }
}
