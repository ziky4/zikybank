package com.ziky.bank.web;

import com.ziky.bank.domain.Account;
import com.ziky.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Map;

/**
 * Created by Ziky on 13.11.2014.
 */

@Controller
public class InfoController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String showInfo(Map<String, Object> model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccountByOwner(authentication.getName());
        model.put("account", account);

        return "info";
    }
}
