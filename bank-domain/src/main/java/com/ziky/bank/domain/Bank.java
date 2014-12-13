package com.ziky.bank.domain;

import java.io.Serializable;

/**
 * Created by Ziky on 1.10.2014.
 */
public class Bank extends Id<Integer> implements Serializable {
    private Integer bankCode;
    private String name;

    public Bank() {
    }

    public Bank(Integer bankCode, String name) {
        this.bankCode = bankCode;
        this.name = name;
    }

    public Integer getBankCode() {
        return bankCode;
    }

    public void setBankCode(Integer bankCode) {
        this.bankCode = bankCode;
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

        Bank bank = (Bank) o;

        if (bankCode != null ? !bankCode.equals(bank.bankCode) : bank.bankCode != null) return false;
        if (name != null ? !name.equals(bank.name) : bank.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bankCode != null ? bankCode.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public Integer getId() {
        return bankCode;
    }
}
