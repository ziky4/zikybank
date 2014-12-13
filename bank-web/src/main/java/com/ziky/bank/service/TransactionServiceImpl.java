package com.ziky.bank.service;

import com.ziky.bank.domain.Tx;
import com.ziky.bank.domain.TxType;
import com.ziky.bank.repository.TxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Ziky on 6.10.2014.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TxDao txDao;

    @Override
    public List<Tx> getAllTransactions() {
        return txDao.findAllTransactions();
    }

    @Override
    public List<Tx> getTransactionsByType(Integer accountNumber, TxType transactionType, Date from, Date to) {
        return txDao.findTransactionsByType(accountNumber, transactionType, from, to);
    }

    @Override
    public List<Tx> getTransactionsBetweenDates(Integer accountNumber, Date from, Date to) {
        return txDao.findTransactionsBetweenDates(accountNumber, from, to);
    }

    @Override
    public List<Tx> getTransactionsByDay(Integer accountNumber, Date date) {
        return txDao.findTransactionsByDay(accountNumber, date);
    }

    @Override
    public List<Tx> getTransactions(Integer accountNumber, TxType transactionType, Date from, Date to) {
        return null;
    }

    @Override
    public Tx getTransactionById(Long id) {
        //return txDao.findTransactionById(id);
        return txDao.find(id);
    }

    @Override
    public void createTransaction(Tx transaction) {
        txDao.create(transaction);
    }

    @Override
    public void deleteAll() {
        txDao.deleteAll();
    }
}
