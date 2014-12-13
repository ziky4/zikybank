package com.ziky.bank.domain;

import java.io.Serializable;

/**
 * Created by Ziky on 1.10.2014.
 */
public class AccountType extends Id<Long> implements Serializable {
    private Long typeId;
    private String name;
    private Float interestRate;
    private Float debitInterestRate;
    private Float monthlyFee;

    public AccountType() {

    }

    public AccountType(String name, Float interestRate, Float debitInterestRate, Float monthlyFee) {
        this.name = name;
        this.interestRate = interestRate;
        this.debitInterestRate = debitInterestRate;
        this.monthlyFee = monthlyFee;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    public Float getDebitInterestRate() {
        return debitInterestRate;
    }

    public void setDebitInterestRate(Float debitInterestRate) {
        this.debitInterestRate = debitInterestRate;
    }

    public Float getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountType that = (AccountType) o;

        if (debitInterestRate != null ? !debitInterestRate.equals(that.debitInterestRate) : that.debitInterestRate != null)
            return false;
        if (interestRate != null ? !interestRate.equals(that.interestRate) : that.interestRate != null) return false;
        if (monthlyFee != null ? !monthlyFee.equals(that.monthlyFee) : that.monthlyFee != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeId != null ? typeId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (interestRate != null ? interestRate.hashCode() : 0);
        result = 31 * result + (debitInterestRate != null ? debitInterestRate.hashCode() : 0);
        result = 31 * result + (monthlyFee != null ? monthlyFee.hashCode() : 0);
        return result;
    }

    @Override
    public Long getId() {
        return typeId;
    }
}
