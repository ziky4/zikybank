package com.ziky.bank.repository;

import com.ziky.bank.domain.AccountType;

import java.util.List;

/**
 * Created by Ziky on 3.10.2014.
 */
public interface AccountTypeDao extends Dao<AccountType> {
    //public AccountType findAccountTypeById(Integer accountTypeId);
    public AccountType findAccountTypeByName(String name);
    public List<AccountType> findAllAccountTypes();
    //public void saveAccountType(AccountType accountType);
    //public void deleteAccountType(AccountType accountType);
    //public void deleteAll();
    //public AccountType updateAccountType(AccountType accountType);
}
