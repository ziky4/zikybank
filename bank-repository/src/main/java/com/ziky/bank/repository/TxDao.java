package com.ziky.bank.repository;

import com.ziky.bank.domain.Tx;
import com.ziky.bank.domain.TxType;

import java.util.Date;
import java.util.List;

/**
 * Created by Ziky on 3.10.2014.
 */
public interface TxDao extends Dao<Tx>{
    //public Tx findTransactionById(Long transactionId);
    public List<Tx> findTransactionsByDay(Integer accountNumber, Date date);
    public List<Tx> findTransactionsBetweenDates(Integer accountNumber, Date startDate, Date endDate);
    public List<Tx> findTransactionsByType(Integer accountNumber, TxType type, Date startDate, Date endDate);
    public List<Tx> findAllTransactions();
    //public void saveTransaction(Tx transaction, boolean isAccountInZikyBank);
    //public void deleteTransaction(Tx transaction);
    //public void deleteAll();
    //public Tx updateTransaction(Tx transaction);
}
