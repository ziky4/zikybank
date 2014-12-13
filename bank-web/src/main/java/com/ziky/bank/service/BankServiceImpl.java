package com.ziky.bank.service;

import com.ziky.bank.domain.Bank;
import com.ziky.bank.repository.BankDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ziky on 14.10.2014.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;

    @Override
    public void createBank(Bank bank) {
        bankDao.create(bank);
    }

    @Override
    public List<Bank> getAll() {
        return bankDao.findAllBanks();
    }

    @Override
    public void deleteAll() {
        bankDao.deleteAll();
    }

    @Override
    public Bank getBankByCode(Integer bankCode) {
        return bankDao.find(bankCode);
    }
}
