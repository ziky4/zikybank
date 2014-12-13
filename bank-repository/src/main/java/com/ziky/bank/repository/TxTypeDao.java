package com.ziky.bank.repository;

import com.ziky.bank.domain.TxType;

import java.util.List;

/**
 * Created by Ziky on 3.10.2014.
 */
public interface TxTypeDao extends Dao<TxType>{
    //public TxType findTransactionTypeById(Long transactionId);
    public TxType findTransactionTypeByDescription(String description);
    public List<TxType> findAllTransactionTypes();
    //public void saveTransactionType(TxType transactionType);
    //public void deleteTransactionType(TxType transactionType);
    //public void deleteAll();
    //public TxType updateTransactionType(TxType transactionType);
}
