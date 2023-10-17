package com.aos.application.service;

import com.aos.core.response.DataResponse;
import com.aos.repo.vo.CurrencyVO;

import java.util.List;

public interface ICurrencyApplication {
    DataResponse<List<CurrencyVO>> all();
}
