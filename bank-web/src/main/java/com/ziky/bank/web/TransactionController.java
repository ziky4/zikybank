package com.ziky.bank.web;

import com.ziky.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by Ziky on 14.11.2014.
 */
@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.GET)
    public String showTransactionDetail(@PathVariable Long id, Map<String, Object> model) {
        model.put("transaction", transactionService.getTransactionById(id));

        return "transactionDetail";
    }
}
