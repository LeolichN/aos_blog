package com.aos.domain.service.impl;

import com.aos.domain.service.ICurrencyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class CurrencyService implements ICurrencyService {
    @Override
    public BigDecimal convert(BigDecimal balance, String fromCode, String toCode) {
        if(StringUtils.equals(fromCode,toCode)){
            return balance;
        }
//        List<CurrencyDetails> currencyList = applicationScopeBean.getCurrencyDetailsList();
//        CurrencyDetails fromCurrency = currencyList.stream().filter(currencyDetails -> fromCode.equals(currencyDetails.getName())).findAny().orElseThrow(ItemNotFoundException::new);
//        CurrencyDetails toCurrency = currencyList.stream().filter(currencyDetails -> toCode.equals(currencyDetails.getName())).findAny().orElseThrow(ItemNotFoundException::new);
//        BigDecimal fromRate = BigDecimal.valueOf(fromCurrency.getRate());
//        BigDecimal toRate = BigDecimal.valueOf(toCurrency.getRate());
        return balance;//toRate.divide(fromRate, 2, RoundingMode.CEILING);
    }
}
