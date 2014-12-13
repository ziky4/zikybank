package com.ziky.bank.domain;

import java.io.Serializable;

/**
 * Created by Ziky on 1.10.2014.
 */
public class Currency extends Id<String> implements Serializable {
    private String code;
    private String name;

    public Currency() {
    }

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (code != null ? !code.equals(currency.code) : currency.code != null) return false;
        if (name != null ? !name.equals(currency.name) : currency.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String getId() {
        return code;
    }
}
