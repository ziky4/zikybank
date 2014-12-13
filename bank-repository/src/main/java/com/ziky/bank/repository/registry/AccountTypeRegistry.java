package com.ziky.bank.repository.registry;

import com.ziky.bank.domain.Account;
import com.ziky.bank.domain.AccountType;

import java.util.HashMap;

/**
 * Created by Ziky on 5.12.2014.
 */
public class AccountTypeRegistry {
    private HashMap<Long, AccountType> data = null;
    private static AccountTypeRegistry instance = null;

    private AccountTypeRegistry() {
        data = new HashMap<Long, AccountType>();
    }

    public static AccountTypeRegistry getInstance() {
        if(instance == null) {
            instance = new AccountTypeRegistry();
        }

        return instance;
    }

    public void addToRegistry(Long key, AccountType value) {
        getInstance().data.put(key, value);
    }

    public AccountType getFromRegistry(Long key) {
        return getInstance().instance.data.get(key);
    }

    public void deleteFromRegistry(Long key) {
        getInstance().data.remove(key);
    }

    public void clearRegistry() {
        getInstance().data.clear();
    }
}
