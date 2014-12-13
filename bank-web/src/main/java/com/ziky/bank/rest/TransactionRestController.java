package com.ziky.bank.rest;

import com.ziky.bank.domain.Tx;
import com.ziky.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ziky on 10.12.2014.
 */
@Controller
public class TransactionRestController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/rest/transactions", method = RequestMethod.GET)
    public @ResponseBody List<Tx> getAllAccounts() {
        return transactionService.getAllTransactions();
    }

    @RequestMapping(value = "/rest/transaction/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody Tx transaction) {
        transactionService.createTransaction(transaction);
    }
}
