package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionDAO;
import com.revature.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServices {

    private final TransactionDAO transactionDAO;

    private final AccountDAO accountDAO;

    @Autowired
    public TransactionServices(TransactionDAO transactionDAO, AccountDAO accountDAO) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }
    public Transaction getTransactionByID(int transactionID) {
        return transactionDAO.getTransactionByID(transactionID);
    }

    public List<Transaction> getTransactionsByAccountID(int accountID) {
        return transactionDAO.getTransactionsByAccountID(accountID);
    }

    public void addTransaction(Transaction transaction) {
        transactionDAO.addTransaction(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        transactionDAO.updateTransaction(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        transactionDAO.deleteTransaction(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }
}
