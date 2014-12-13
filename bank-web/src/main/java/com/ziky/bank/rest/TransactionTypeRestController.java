package com.ziky.bank.rest;

import com.ziky.bank.domain.TxType;
import com.ziky.bank.service.TxTypeService;
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
public class TransactionTypeRestController {
    @Autowired
    private TxTypeService txTypeService;

    @RequestMapping(value = "/rest/txtypes", method = RequestMethod.GET)
    public @ResponseBody List<TxType> getTxTypes() {
        return txTypeService.getDepositAndWithdraw();
    }
}
