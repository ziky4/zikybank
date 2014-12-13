package com.ziky.bank.service;

import com.ziky.bank.domain.TxType;

import java.util.List;

/**
 * Created by Ziky on 14.10.2014.
 */
public interface TxTypeService {
    public void createTxType(TxType type);
    public TxType getTransactionTypeByName(String name);
    public List<TxType> getDepositAndWithdraw();
    public void deleteAll();
}
