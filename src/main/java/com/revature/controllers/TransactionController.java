package com.revature.controllers;

import com.revature.models.Transaction;
import com.revature.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController
{
    private final TransactionServices transactionServices;

    @Autowired
    public TransactionController(TransactionServices transactionServices) {
        this.transactionServices = transactionServices;
    }

    @GetMapping("/{transactionID}")
    public Transaction getTransactionByID(@PathVariable int transactionID) {
        return transactionServices.getTransactionByID(transactionID);
    }

    @GetMapping("/accounts/{account}")
    public List<Transaction> getTransactionsByAccount(@PathVariable("account") String account) {
        return transactionServices.getTransactionsByAccount(account);
    }


    @PostMapping("/add")
    public void addTransaction(@RequestBody Transaction transaction) {
        transactionServices.addTransaction(transaction);
    }

    @PutMapping("/update")
    public void updateTransaction(@RequestBody Transaction transaction) {
        transactionServices.updateTransaction(transaction);
    }

    @DeleteMapping("/delete")
    public void deleteTransaction(@RequestBody Transaction transaction) {
        transactionServices.deleteTransaction(transaction);
    }

}
