package com.ziky.bank.repository.registry;

import com.ziky.bank.domain.Person;

import java.util.HashMap;

/**
 * Created by Ziky on 5.12.2014.
 */
public class PersonRegistry {
    private HashMap<String, Person> data = null;
    private static PersonRegistry instance = null;

    private PersonRegistry() {
        data = new HashMap<String, Person>();
    }

    public static PersonRegistry getInstance() {
        if(instance == null) {
            instance = new PersonRegistry();
        }

        return instance;
    }

    public void addToRegistry(String key, Person value) {
        getInstance().data.put(key, value);
    }

    public Person getFromRegistry(String key) {
        return getInstance().instance.data.get(key);
    }

    public void deleteFromRegistry(String key) {
        getInstance().data.remove(key);
    }

    public void clearRegistry() {
        getInstance().data.clear();
    }
}
