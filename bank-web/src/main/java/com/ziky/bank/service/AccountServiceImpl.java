package com.ziky.bank.service;

import com.ziky.bank.domain.Account;
import com.ziky.bank.repository.AccountDao;
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
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account getAccountByOwner(String ownerLogin) {
        return accountDao.findAccountByOwner(ownerLogin);
    }

    @Override
    public Account getAccountDetails(Integer accountNumber) {
        return accountDao.find(accountNumber);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDao.findAllAccounts();
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.update(account);
    }

    @Override
    public void deleteAll() {
        accountDao.deleteAll();
    }

    @Override
    public void createAccount(Account account) {
        accountDao.create(account);
    }
}
