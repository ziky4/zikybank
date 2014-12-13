package com.ziky.bank.service;

import com.ziky.bank.domain.Account;
import com.ziky.bank.exceptions.PaymentException;

/**
 * Created by Ziky on 6.10.2014.
 */
public interface PaymentService {
    public boolean createPayment(Integer accountFrom, Integer accountTo, Integer bankCode, Float sum) throws PaymentException;
}
