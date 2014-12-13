package com.ziky.bank.repository.registry;

import java.util.HashMap;

/**
 * Created by Ziky on 5.12.2014.
 */
public abstract class Registry<T> {
    protected HashMap<Object, T> data = null;
    protected static Registry instance = null;

    protected Registry() {
        data = new HashMap<Object, T>();
    }

    public abstract Registry getInstance();

    public void addToRegistry(Object key, Object value) {
        HashMap<Object, T> tmp = getInstance().data;
        getInstance().data.put(key, value);
    }

    public Object getFromRegistry(Object key) {
        return getInstance().instance.data.get(key);
    }
}
