package com.ziky.bank.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ziky on 1.10.2014.
 */
public class Tx extends Id<Long> implements Serializable {
    private Long txId;
    private Date creationDate;
    private Float amount;
    private Currency currencyCode;
    private Bank bankCode;
    private Account accountIdFrom;
    private Account accountIdTo;
    private TxType typeId;

    public Tx() {
    }

    public Tx(Float amount, Currency currency, Bank code, Account from, Account to, TxType type) {
        this.creationDate = new Date();//Calendar.getInstance().getTime();
        this.amount = amount;
        this.currencyCode = currency;
        this.bankCode = code;
        this.accountIdFrom = from;
        this.accountIdTo = to;
        this.typeId = type;
    }

    public Long getTxId() {
        return txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Currency getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Currency currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Bank getBankCode() {
        return bankCode;
    }

    public void setBankCode(Bank bankCode) {
        this.bankCode = bankCode;
    }

    public Account getAccountIdFrom() {
        return accountIdFrom;
    }

    public void setAccountIdFrom(Account accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
    }

    public Account getAccountIdTo() {
        return accountIdTo;
    }

    public void setAccountIdTo(Account accountIdTo) {
        this.accountIdTo = accountIdTo;
    }

    public TxType getTypeId() {
        return typeId;
    }

    public void setTypeId(TxType typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tx tx = (Tx) o;

        if (accountIdFrom != null ? !accountIdFrom.equals(tx.accountIdFrom) : tx.accountIdFrom != null) return false;
        if (accountIdTo != null ? !accountIdTo.equals(tx.accountIdTo) : tx.accountIdTo != null) return false;
        if (amount != null ? !amount.equals(tx.amount) : tx.amount != null) return false;
        if (bankCode != null ? !bankCode.equals(tx.bankCode) : tx.bankCode != null) return false;
        if (creationDate != null ? !creationDate.equals(tx.creationDate) : tx.creationDate != null) return false;
        if (currencyCode != null ? !currencyCode.equals(tx.currencyCode) : tx.currencyCode != null) return false;
        if (txId != null ? !txId.equals(tx.txId) : tx.txId != null) return false;
        if (typeId != null ? !typeId.equals(tx.typeId) : tx.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = txId != null ? txId.hashCode() : 0;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
        result = 31 * result + (bankCode != null ? bankCode.hashCode() : 0);
        result = 31 * result + (accountIdFrom != null ? accountIdFrom.hashCode() : 0);
        result = 31 * result + (accountIdTo != null ? accountIdTo.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        return result;
    }

    @Override
    public Long getId() {
        return txId;
    }
}
