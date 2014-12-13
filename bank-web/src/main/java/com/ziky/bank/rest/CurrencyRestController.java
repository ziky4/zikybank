package com.ziky.bank.rest;

import com.ziky.bank.domain.Currency;
import com.ziky.bank.service.CurrencyService;
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
public class CurrencyRestController {
    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/rest/currencies", method = RequestMethod.GET)
    public @ResponseBody List<Currency> getAllCurrencies() {
        return currencyService.getAll();
    }
}
