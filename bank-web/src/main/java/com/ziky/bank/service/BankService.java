package com.ziky.bank.service;

import com.ziky.bank.domain.Bank;

import java.util.List;

/**
 * Created by Ziky on 14.10.2014.
 */
public interface BankService {
    public void createBank(Bank bank);
    public List<Bank> getAll();
    public void deleteAll();
    public Bank getBankByCode(Integer bankCode);
}
