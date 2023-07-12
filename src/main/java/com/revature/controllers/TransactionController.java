package com.revature.controllers;

import com.revature.dtos.TransactionDTO;
import com.revature.models.Transaction;
import com.revature.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TransactionController {
  private final TransactionServices transactionServices;

  @Autowired
  public TransactionController(TransactionServices transactionServices) {
    this.transactionServices = transactionServices;
  }

  @PostMapping("/cc-transaction")
  public ResponseEntity<?> creditCardTransactionHandler(
    @RequestBody TransactionDTO transaction) {
    if (transaction != null) {
      if (!transaction.getType().getType().equals("Credit") &&
          !transaction.getType().getType().equals("Debit")) {
        return new ResponseEntity<>(
          "Transaction type was invalid for CC transactions",
          HttpStatus.BAD_REQUEST);
      }

      Transaction complete = null;

      if (transaction.getType().getType().equals("Credit")) {
        complete = transactionServices.creditUserCC(transaction.getCreditCard(),
                                                    transaction.getTransactionAcctId(),
                                                    transaction.getAmount());
      }

      if (transaction.getType().getType().equals("Debit")) {
        complete = transactionServices.debitUserCC(transaction.getCreditCard(),
                                                   transaction.getTransactionAcctId(),
                                                   transaction.getAmount());
      }

      if (complete != null && complete.getTransactionID() > 0) {
        return new ResponseEntity<>(complete, HttpStatus.CREATED);
      }
    }

    return new ResponseEntity<>("Could not complete transaction",
                                HttpStatus.BAD_REQUEST);
  }
}