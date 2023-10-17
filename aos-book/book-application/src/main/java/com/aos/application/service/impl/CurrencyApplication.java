package com.aos.application.service.impl;

import com.aos.application.service.ICurrencyApplication;
import com.aos.core.response.DataResponse;
import com.aos.repo.vo.CurrencyVO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyApplication implements ICurrencyApplication {

    @Override
    public DataResponse<List<CurrencyVO>> all() {
        CurrencyVO currencyVO = new CurrencyVO();
        currencyVO.setName("人民币");
        currencyVO.setCode("CNY");
        currencyVO.setRate(1.1d);
    return new DataResponse<>(Arrays.asList(currencyVO));
    }
}
