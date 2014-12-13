package com.ziky.bank.service;

import com.ziky.bank.domain.Account;
import com.ziky.bank.domain.AccountType;
import com.ziky.bank.repository.AccountTypeDao;
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
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    private AccountTypeDao accountTypeDao;

    @Override
    public void createAccountType(AccountType accountType) {
        accountTypeDao.create(accountType);
    }

    @Override
    public AccountType getAccountByName(String name) {
        return accountTypeDao.findAccountTypeByName(name);
    }

    @Override
    public void deleteAll() {
        accountTypeDao.deleteAll();
    }

    @Override
    public List<AccountType> getAll() {
        return accountTypeDao.findAllAccountTypes();
    }
}
