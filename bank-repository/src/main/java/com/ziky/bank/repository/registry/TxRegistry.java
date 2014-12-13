package com.ziky.bank.repository.registry;

import com.ziky.bank.domain.Tx;

import java.util.HashMap;

/**
 * Created by Ziky on 5.12.2014.
 */
public class TxRegistry {
    private HashMap<Long, Tx> data = null;
    private static TxRegistry instance = null;

    private TxRegistry() {
        data = new HashMap<Long, Tx>();
    }

    public static TxRegistry getInstance() {
        if(instance == null) {
            instance = new TxRegistry();
        }

        return instance;
    }

    public void addToRegistry(Long key, Tx value) {
        getInstance().data.put(key, value);
    }

    public Tx getFromRegistry(Long key) {
        return getInstance().instance.data.get(key);
    }

    public void deleteFromRegistry(Long key) {
        getInstance().data.remove(key);
    }

    public void clearRegistry() {
        getInstance().data.clear();
    }
}
