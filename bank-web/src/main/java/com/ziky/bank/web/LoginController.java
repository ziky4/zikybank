package com.ziky.bank.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Ziky on 7.11.2014.
 */

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(@RequestParam(value = "login_error", required = false) String error, Map<String, Object> model) {
        if(!(error == null)) {
            model.put("error", "Invalid account number or password");
        }

        return "login";
    }
}
