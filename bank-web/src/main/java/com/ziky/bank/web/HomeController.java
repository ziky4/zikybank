package com.ziky.bank.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ziky on 8.10.2014.
 */

@Controller
public class HomeController {

    @RequestMapping({"/", "home"})
    public String home() {
        return "home";
    }
}
