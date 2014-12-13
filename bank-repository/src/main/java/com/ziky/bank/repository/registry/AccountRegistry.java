package com.ziky.bank.repository.registry;

import com.ziky.bank.domain.Account;

import java.util.HashMap;

/**
 * Created by Ziky on 5.12.2014.
 */
public class AccountRegistry {
    protected HashMap<Integer, Account> data = null;
    protected static AccountRegistry instance = null;

    private AccountRegistry() {
        data = new HashMap<Integer, Account>();
    }

    public static AccountRegistry getInstance() {
        if(instance == null) {
            instance = new AccountRegistry();
        }

        return instance;
    }

    public void addToRegistry(Integer key, Account value) {
        getInstance().data.put(key, value);
    }

    public Account getFromRegistry(Integer key) {
        return getInstance().data.get(key);
    }

    public void deleteFromRegistry(Integer key) {
        getInstance().data.remove(key);
    }

    public void clearRegistry() {
        getInstance().data.clear();
    }
}
