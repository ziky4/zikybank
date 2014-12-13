package com.ziky.bank.repository.registry;

import com.ziky.bank.domain.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ziky on 5.12.2014.
 */
public class BankRegistry {
    private HashMap<Integer, Bank> data = null;
    private static BankRegistry instance = null;

    private BankRegistry() {
        data = new HashMap<Integer, Bank>();
    }

    public static BankRegistry getInstance() {
        if(instance == null) {
            instance = new BankRegistry();
        }

        return instance;
    }

    public void addToRegistry(Integer key, Bank value) {
        getInstance().data.put(key, value);
    }

    public Bank getFromRegistry(Integer key) {
        return getInstance().instance.data.get(key);
    }

    public void deleteFromRegistry(Integer key) {
        getInstance().data.remove(key);
    }

    public void clearRegistry() {
        getInstance().data.clear();
    }

    public List<Bank> getAll() {
        List<Bank> banks = new ArrayList<>();

        for(Bank bank : getInstance().data.values()) {
            banks.add(bank);
        }

        return banks;
    }
}
