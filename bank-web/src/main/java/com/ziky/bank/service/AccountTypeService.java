package com.ziky.bank.service;

import com.ziky.bank.domain.AccountType;

import java.util.List;

/**
 * Created by Ziky on 14.10.2014.
 */
public interface AccountTypeService {
    public void createAccountType(AccountType accountType);
    public AccountType getAccountByName(String name);
    public void deleteAll();
    public List<AccountType> getAll();
}
