package com.aos.domain.service;

import java.math.BigDecimal;

public interface ICurrencyService {
    BigDecimal convert(BigDecimal balance, String currencyCode, String defaultCurrencyCode);
}
