package com.ziky.bank.repository;

import com.ziky.bank.domain.Bank;

import java.util.List;

/**
 * Created by Ziky on 3.10.2014.
 */
public interface BankDao extends Dao<Bank>{
    //public Bank findBanById(Integer bankId);
    public List<Bank> findAllBanks();
    //public void saveBank(Bank bank);
    //public void deleteBank(Bank bank);
    //public void deleteAll();
    //public Bank updateBank(Bank bank);
}
