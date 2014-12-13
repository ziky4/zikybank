package com.ziky.bank.service;

import com.ziky.bank.domain.Account;

import java.util.List;

/**
 * Created by Ziky on 6.10.2014.
 */
public interface AccountService {
    public Account getAccountByOwner(String ownerLogin);
    public Account getAccountDetails(Integer accountNumber);
    public List<Account> getAllAccounts();
    public void updateAccount(Account account);
    public void deleteAll();
    public void createAccount(Account account);
}
