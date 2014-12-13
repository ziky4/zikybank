package com.ziky.bank.repository;

import com.ziky.bank.domain.Account;

import java.util.List;

/**
 * Created by Ziky on 3.10.2014.
 */
public interface AccountDao extends Dao<Account> {
    public Account findAccountByOwner(String ownerLogin);
    //public Account findAccountById(Integer accountId);
    public List<Account> findAllAccounts();
    //public void saveAccount(Account account);
    //public void deleteAccount(Account account);
    //public void deleteAll();
    //public Account updateAccount(Account account);
}
