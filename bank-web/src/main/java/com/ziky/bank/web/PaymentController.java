package com.ziky.bank.web;

import com.ziky.bank.domain.Account;
import com.ziky.bank.exceptions.PaymentException;
import com.ziky.bank.helper.PaymentHelper;
import com.ziky.bank.service.AccountService;
import com.ziky.bank.service.BankService;
import com.ziky.bank.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * Created by Ziky on 7.11.2014.
 */

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BankService bankService;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(method = RequestMethod.GET)
    public String showPayment(Map<String, Object> model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccountByOwner(authentication.getName());

        model.put("time", new Date());
        model.put("banks", bankService.getAll());
        model.put("currency", account.getCurrencyCode().getName());
        model.put("accountNumber", account.getAccountNumber());
        model.put("paymentHelper", new PaymentHelper());

        return "payment";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createPayment(@Valid PaymentHelper paymentHelper, BindingResult bindingResult, Map<String, Object> model) {

        if(bindingResult.hasErrors()) {
            return "payment";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer accountNumberFrom = accountService.getAccountByOwner(authentication.getName()).getAccountNumber();
        try {
            paymentService.createPayment(accountNumberFrom,
                    paymentHelper.getAccountTo(),
                    paymentHelper.getBankCode(),
                    paymentHelper.getAmount());
        } catch (PaymentException e) {
            model.put("message", e.getMessage());
            model.put("title", "Transaction canceled");
            return "infoStatus";
        }

        model.put("title", "Success");
        model.put("message", "Transaction completed");
        return "infoStatus";
    }
}
