package com.ziky.bank.exceptions;

/**
 * Created by Ziky on 14.11.2014.
 */
public class PaymentException extends RuntimeException {

    public PaymentException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
