package com.ziky.bank.repository.registry;

import com.ziky.bank.domain.Role;

import java.util.HashMap;

/**
 * Created by Ziky on 5.12.2014.
 */
public class RoleRegistry {
    private HashMap<String, Role> data = null;
    private static RoleRegistry instance = null;

    private RoleRegistry() {
        data = new HashMap<String, Role>();
    }

    public static RoleRegistry getInstance() {
        if(instance == null) {
            instance = new RoleRegistry();
        }

        return instance;
    }

    public void addToRegistry(String key, Role value) {
        getInstance().data.put(key, value);
    }

    public Role getFromRegistry(String key) {
        return getInstance().instance.data.get(key);
    }

    public void deleteFromRegistry(String key) {
        getInstance().data.remove(key);
    }

    public void clearRegistry() {
        getInstance().data.clear();
    }
}
