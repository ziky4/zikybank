package com.ziky.bank.service;

import com.ziky.bank.domain.TxType;
import com.ziky.bank.repository.TxTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ziky on 14.10.2014.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TxTypeServiceImpl implements TxTypeService {

    @Autowired
    private TxTypeDao txTypeDao;

    @Override
    public void createTxType(TxType type) {
        txTypeDao.create(type);
    }

    @Override
    public TxType getTransactionTypeByName(String name) {
        return txTypeDao.findTransactionTypeByDescription(name);
    }

    @Override
    public List<TxType> getDepositAndWithdraw() {
        List<TxType> result = new ArrayList<>();

        result.add(txTypeDao.findTransactionTypeByDescription("deposit"));
        result.add(txTypeDao.findTransactionTypeByDescription("withdraw"));

        return result;
    }

    @Override
    public void deleteAll() {
        txTypeDao.deleteAll();
    }
}
