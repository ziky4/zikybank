package com.ziky.bank.repository.registry;

import com.ziky.bank.domain.TxType;

import java.util.HashMap;

/**
 * Created by Ziky on 5.12.2014.
 */
public class TxTypeRegistry {
    private HashMap<Long, TxType> data = null;
    private static TxTypeRegistry instance = null;

    private TxTypeRegistry() {
        data = new HashMap<Long, TxType>();
    }

    public static TxTypeRegistry getInstance() {
        if(instance == null) {
            instance = new TxTypeRegistry();
        }

        return instance;
    }

    public void addToRegistry(Long key, TxType value) {
        getInstance().data.put(key, value);
    }

    public TxType getFromRegistry(Long key) {
        return getInstance().instance.data.get(key);
    }

    public void deleteFromRegistry(Long key) {
        getInstance().data.remove(key);
    }

    public void clearRegistry() {
        getInstance().data.clear();
    }
}
