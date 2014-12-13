package com.ziky.bank.web;

import com.ziky.bank.domain.Tx;
import com.ziky.bank.helper.ListingHelper;
import com.ziky.bank.service.AccountService;
import com.ziky.bank.service.TransactionService;
import com.ziky.bank.service.TxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ziky on 7.11.2014.
 */

@Controller
@RequestMapping(value = "/listing")
public class ListingController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TxTypeService txTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public String showListingPage(Map<String, Object> model) {
        model.put("listingHelper", new ListingHelper());
        model.put("time", new Date());

        return "listing";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fillTableWithTransactions(@Valid ListingHelper listingHelper, BindingResult bindingResult, Map<String, Object> model) {

        if(bindingResult.hasErrors()) {
            return "listing";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer accountNumber = accountService.getAccountByOwner(authentication.getName()).getAccountNumber();

        List<Tx> transactions = new ArrayList<Tx>();

        if(listingHelper.getType().equals("All")) {
            transactions = transactionService.getTransactionsBetweenDates(accountNumber, listingHelper.getStartDate(),
                    listingHelper.getEndDate());
        } else {
            transactions = transactionService.getTransactionsByType(accountNumber,
                    txTypeService.getTransactionTypeByName(listingHelper.getType().toLowerCase()),
                    listingHelper.getStartDate(), listingHelper.getEndDate());
        }

        if(transactions.isEmpty()) {
            model.put("text", "Nothing found");
        }
        model.put("transactions", transactions);

        return "listing";
    }
}
