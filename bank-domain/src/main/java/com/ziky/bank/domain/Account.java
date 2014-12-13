package com.ziky.bank.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ziky on 1.10.2014.
 */
public class Account extends Id<Integer> implements Serializable {
    private Integer accountNumber;
    private Date creationTime;
    private Float balance;
    private Float overDraft;
    private Float dailyLimit;
    private Float monthlyLimit;
    private AccountType typeId;
    private Currency currencyCode;
    private Person owner;

    public Account() {
    }

    public Account(Integer accountNumber, Float balance, Float overDraft, Float dailyLimit, Float monthlyLimit, AccountType typeId, Currency currencyCode, Person owner) {
        this.accountNumber = accountNumber;
        this.creationTime = Calendar.getInstance().getTime();
        this.balance = balance;
        this.overDraft = overDraft;
        this.dailyLimit = dailyLimit;
        this.monthlyLimit = monthlyLimit;
        this.typeId = typeId;
        this.currencyCode = currencyCode;
        this.owner = owner;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(Float overDraft) {
        this.overDraft = overDraft;
    }

    public Float getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Float dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public Float getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(Float monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public AccountType getTypeId() {
        return typeId;
    }

    public void setTypeId(AccountType typeId) {
        this.typeId = typeId;
    }

    public Currency getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Currency currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountNumber != null ? !accountNumber.equals(account.accountNumber) : account.accountNumber != null)
            return false;
        if (balance != null ? !balance.equals(account.balance) : account.balance != null) return false;
        if (creationTime != null ? !creationTime.equals(account.creationTime) : account.creationTime != null)
            return false;
        if (currencyCode != null ? !currencyCode.equals(account.currencyCode) : account.currencyCode != null)
            return false;
        if (dailyLimit != null ? !dailyLimit.equals(account.dailyLimit) : account.dailyLimit != null) return false;
        if (monthlyLimit != null ? !monthlyLimit.equals(account.monthlyLimit) : account.monthlyLimit != null)
            return false;
        if (overDraft != null ? !overDraft.equals(account.overDraft) : account.overDraft != null) return false;
        if (owner != null ? !owner.equals(account.owner) : account.owner != null) return false;
        if (typeId != null ? !typeId.equals(account.typeId) : account.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountNumber != null ? accountNumber.hashCode() : 0;
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (overDraft != null ? overDraft.hashCode() : 0);
        result = 31 * result + (dailyLimit != null ? dailyLimit.hashCode() : 0);
        result = 31 * result + (monthlyLimit != null ? monthlyLimit.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    @Override
    public Integer getId() {
        return accountNumber;
    }
}
