package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionServices {

    private final TransactionDAO transactionDAO;

    private final AccountDAO accountDAO;

    @Autowired
    public TransactionServices(TransactionDAO transactionDAO, AccountDAO accountDAO) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }
}
