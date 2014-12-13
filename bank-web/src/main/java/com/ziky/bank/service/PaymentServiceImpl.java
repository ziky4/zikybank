package com.ziky.bank.service;

import com.ziky.bank.domain.Account;
import com.ziky.bank.domain.Tx;
import com.ziky.bank.exceptions.PaymentException;
import com.ziky.bank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ziky on 6.10.2014.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TxDao txDao;
    @Autowired
    private TxTypeDao txTypeDao;
    @Autowired
    private BankDao bankDao;

    @Override
    public boolean createPayment(Integer accountFrom, Integer accountTo, Integer bankCode, Float sum) throws PaymentException{
        if(sum == null) {
            throw new PaymentException("Please enter amount");
        }

        Account accFrom =  accountDao.find(accountFrom);
        Account accTo = null;
        boolean isAccountInZikyBank = true;

        /* check if accountTo is from zikyBank */
        if(bankCode == 4444) {
            accTo = accountDao.find(accountTo);
            if(accTo == null) {
                throw new PaymentException("Account from doesn't exist");
            }
        } else {
            isAccountInZikyBank = false;
        }

        Float dailyLimit = new Float(0);
        Float monthlyLimit = new Float(0);

        /* get sum of all payments for given day */
        List<Tx> dailyTransactions = txDao.findTransactionsByDay(accFrom.getAccountNumber(), new Date());
        for(Tx transaction : dailyTransactions) {
            dailyLimit += transaction.getAmount();
        }

        /* set startDate = today and endDate = today + month */
        Date startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();

        /* get sum of all payments in month */
        List<Tx> monthlyTransactions = txDao.findTransactionsBetweenDates(accFrom.getAccountNumber(), startDate, endDate);
        for(Tx transaction : monthlyTransactions) {
            monthlyLimit += transaction.getAmount();
        }

        /* check if daily limit was exceeded */
        if((dailyLimit + sum) > accFrom.getDailyLimit()) {
            throw new PaymentException("You exceed daily limit");
        }

        /* check if monthly limit was exceeded */
        if((monthlyLimit + sum) > accFrom.getMonthlyLimit()) {
            throw new PaymentException("You exceed monthly limit");
        }

        /* check if overdraft was exceeded */
        if((accFrom.getBalance() - sum) < (accFrom.getOverDraft() * -1)) {
            throw new PaymentException("You exceed overdraft");
        }

        /* set accountFrom balance to new value */
        accFrom.setBalance(accFrom.getBalance() - sum);
        accountDao.update(accFrom);

        /* convert amount to right currency */
        /* set accountTo balance to new value  */
        if(isAccountInZikyBank) {
            if(accFrom.getCurrencyCode().getCode().equals(accTo.getCurrencyCode().getCode())) {
                accTo.setBalance(accTo.getBalance() + sum);
            } else {
                accTo.setBalance(accTo.getBalance()
                        + (sum * getRate(accFrom.getCurrencyCode().getCode(),
                                         accTo.getCurrencyCode().getCode())));
            }

            accountDao.update(accTo);
        }

        if(!isAccountInZikyBank) {
            accTo = new Account();
            accTo.setAccountNumber(accountTo);
        }

        /* save transaction information to database */
        //txDao.saveTransaction(new Tx(sum, accFrom.getCurrencyCode(),
        //        bankDao.findBanById(bankCode), accFrom, accTo,
        //        txTypeDao.findTransactionTypeByDescription("transfer")), true/*isAccountInZikyBank*/);
        txDao.create(new Tx(sum, accFrom.getCurrencyCode(),
                bankDao.find(bankCode), accFrom, accTo,
                txTypeDao.findTransactionTypeByDescription("transfer")));

        return true;
    }

    private Float getRate(String currencyFrom, String currencyTo) {
        if(currencyFrom.equals("cz") && currencyTo.equals("us")) {
            return new Float(0.05);
        } else if(currencyFrom.equals("cz") && currencyTo.equals("en")) {
            return new Float(0.04);
        } else if(currencyFrom.equals("us") && currencyTo.equals("cz")) {
            return new Float(20);
        } else if(currencyFrom.equals("us") && currencyTo.equals("en")) {
            return new Float(0.8);
        } else if(currencyFrom.equals("en") && currencyTo.equals("cz")) {
            return new Float(25);
        }

        return new Float(1.25);
    }
}
