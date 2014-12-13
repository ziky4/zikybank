package com.ziky.bank.repository.registry;

import com.ziky.bank.domain.Currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ziky on 5.12.2014.
 */
public class CurrencyRegistry {
    private HashMap<String, Currency> data = null;
    private static CurrencyRegistry instance = null;

    private CurrencyRegistry() {
        data = new HashMap<String, Currency>();
    }

    public static CurrencyRegistry getInstance() {
        if(instance == null) {
            instance = new CurrencyRegistry();
        }

        return instance;
    }

    public void addToRegistry(String key, Currency value) {
        getInstance().data.put(key, value);
    }

    public Currency getFromRegistry(String key) {
        return getInstance().instance.data.get(key);
    }

    public void deleteFromRegistry(String key) {
        getInstance().data.remove(key);
    }

    public void clearRegistry() {
        getInstance().data.clear();
    }

    public List<Currency> getAll() {
        List<Currency> currencies = new ArrayList<>();

        for(Currency currency : getInstance().data.values()) {
            currencies.add(currency);
        }

        return currencies;
    }
}
