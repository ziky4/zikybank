package com.ziky.bank.helper;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Ziky on 17.11.2014.
 */
@Component
public class PaymentHelper {
    @NotNull(message = "Please enter account number")
    private Integer accountTo;
    @NotNull
    private Integer bankCode;
    @NotNull(message = "Please enter amount")
    private Float amount;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date creationDate;

    public PaymentHelper() {
    }

    public PaymentHelper(Integer accountTo, Integer bankCode, Float amount, Date creationDate) {
        this.accountTo = accountTo;
        this.bankCode = bankCode;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }

    public Integer getBankCode() {
        return bankCode;
    }

    public void setBankCode(Integer bankCode) {
        this.bankCode = bankCode;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentHelper that = (PaymentHelper) o;

        if (accountTo != null ? !accountTo.equals(that.accountTo) : that.accountTo != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (bankCode != null ? !bankCode.equals(that.bankCode) : that.bankCode != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountTo != null ? accountTo.hashCode() : 0;
        result = 31 * result + (bankCode != null ? bankCode.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
