package com.ziky.bank.service;

import com.ziky.bank.domain.Currency;
import com.ziky.bank.repository.CurrencyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ziky on 14.10.2014.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyDao currencyDao;

    @Override
    public void createCurrency(Currency currency) {
        currencyDao.create(currency);
    }

    @Override
    public void deleteAll() {
        currencyDao.deleteAll();
    }

    @Override
    public List<Currency> getAll() {
        return currencyDao.findAllCurrencies();
    }
}
