package com.ziky.bank.repository;

import com.ziky.bank.domain.Currency;

import java.util.List;

/**
 * Created by Ziky on 3.10.2014.
 */
public interface CurrencyDao extends Dao<Currency> {
    //public Currency findCurrencyByCode(String currencyCode);
    public List<Currency> findAllCurrencies();
    //public void saveCurrency(Currency currency);
    //public void deleteCurrency(Currency currency);
    //public void deleteAll();
    //public Currency updateCurrency(Currency currency);
}
