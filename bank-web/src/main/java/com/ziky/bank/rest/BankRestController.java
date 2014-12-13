package com.ziky.bank.rest;

import com.ziky.bank.domain.Bank;
import com.ziky.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ziky on 12.12.2014.
 */
@Controller
public class BankRestController {
    @Autowired
    private BankService bankService;

    @RequestMapping(value = "/rest/bank/{code}", method = RequestMethod.GET)
    public @ResponseBody Bank getBank(@PathVariable("code") Integer code) {
        return bankService.getBankByCode(code);
    }
}
