package com.ziky.bank.service;

import com.ziky.bank.domain.Currency;

import java.util.List;

/**
 * Created by Ziky on 14.10.2014.
 */
public interface CurrencyService {
    public void createCurrency(Currency currency);
    public void deleteAll();
    public List<Currency> getAll();
}
