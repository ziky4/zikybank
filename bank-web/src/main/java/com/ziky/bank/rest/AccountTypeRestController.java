package com.ziky.bank.rest;

import com.ziky.bank.domain.AccountType;
import com.ziky.bank.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Ziky on 12.12.2014.
 */
@Controller
public class AccountTypeRestController {
    @Autowired
    private AccountTypeService accountTypeService;

    @RequestMapping(value = "/rest/accounttypes", method = RequestMethod.GET)
    public @ResponseBody List<AccountType> getAllAccountTypes() {
        return accountTypeService.getAll();
    }
}
