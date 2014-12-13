package com.ziky.bank.rest;

import com.ziky.bank.domain.Account;
import com.ziky.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ziky on 10.12.2014.
 */
@Controller
public class AccountRestController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/rest/accounts", method = RequestMethod.GET)
    public @ResponseBody List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @RequestMapping(value = "/rest/account/{id}", method = RequestMethod.GET)
    public @ResponseBody Account getAccount(@PathVariable("id") Integer id) {
        return accountService.getAccountDetails(id);
    }

    @RequestMapping(value = "/rest/account/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody Account account) {
        accountService.createAccount(account);
    }

    @RequestMapping(value = "/rest/account/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccount(@PathVariable("id") Integer id, @RequestBody Account account) {
        accountService.updateAccount(account);
    }
}
