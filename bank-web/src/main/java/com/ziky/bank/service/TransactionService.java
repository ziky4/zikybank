package com.ziky.bank.service;

import com.ziky.bank.domain.Tx;
import com.ziky.bank.domain.TxType;

import java.util.Date;
import java.util.List;

/**
 * Created by Ziky on 6.10.2014.
 */
public interface TransactionService {
    public List<Tx> getAllTransactions();
    public List<Tx> getTransactionsByType(Integer accountNumber, TxType transactionType, Date from, Date to);
    public List<Tx> getTransactionsBetweenDates(Integer accountNumber, Date from, Date to);
    public List<Tx> getTransactionsByDay(Integer accountNumber, Date date);
    public List<Tx> getTransactions(Integer accountNumber, TxType transactionType, Date from, Date to);
    public Tx getTransactionById(Long id);
    public void createTransaction(Tx transaction);
    public void deleteAll();
}
