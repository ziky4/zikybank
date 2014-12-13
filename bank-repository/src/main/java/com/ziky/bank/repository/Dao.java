package com.ziky.bank.repository;

/**
 * Created by Ziky on 5.12.2014.
 */
public interface Dao<T> {
    void create(T t);
    void delete(T t);
    void deleteAll();
    T find(Object id);
    void update(T t);
}
