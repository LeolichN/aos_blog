package com.aos.interfaces.impl;

import com.aos.application.service.ICurrencyApplication;
import com.aos.core.response.BaseResponse;
import com.aos.interfaces.ICurrencyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyInterface implements ICurrencyInterface {

    @Autowired
    private ICurrencyApplication currencyApplication;

    @Override
    public BaseResponse handleAll() {
        return currencyApplication.all();
    }
}
