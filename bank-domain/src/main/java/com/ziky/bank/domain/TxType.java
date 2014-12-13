package com.ziky.bank.domain;


import java.io.Serializable;

/**
 * Created by Ziky on 1.10.2014.
 */
public class TxType extends Id<Long> implements Serializable {
    private Long typeId;
    private String description;

    public TxType() {
    }

    public TxType(String description) {
        this.description = description;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TxType txType = (TxType) o;

        if (description != null ? !description.equals(txType.description) : txType.description != null) return false;
        if (typeId != null ? !typeId.equals(txType.typeId) : txType.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeId != null ? typeId.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public Long getId() {
        return typeId;
    }
}
