package com.revature.controllers;

import com.revature.dtos.AccountSummaryDTO;
import com.revature.dtos.EmailDTO;
import com.revature.dtos.TransactionDTO;
import com.revature.dtos.UserToUserDTO;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.TransactionServices;
import com.revature.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:3000"})
public class TransactionController {
  private final TransactionServices transactionServices;
  private final UserServices userServices;
  private final AccountServices accountServices;

  @Autowired
  public TransactionController(TransactionServices transactionServices,
                               UserServices userServices,
                               AccountServices accountServices) {
    this.transactionServices = transactionServices;
    this.userServices = userServices;
    this.accountServices = accountServices;
  }

  @PostMapping("/transactions/cc-transaction")
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

  @GetMapping("/transfers/{eml}")
  public ResponseEntity<?> userAccountFetchHandler(@PathVariable("eml") String eml) {
    if (eml == null) {
      return new ResponseEntity<>("Email information was null",
                                  HttpStatus.BAD_REQUEST);
    }

    User user = userServices.getUserByEmail(eml);

    if (user != null) {
      AccountSummaryDTO accountSummary = new AccountSummaryDTO();
      accountSummary.setSummary(accountServices.getAccountSummary(user));
      accountSummary.setFirstName(user.getFirstName());
      accountSummary.setLastName(user.getLastName());

      return new ResponseEntity<>(accountSummary, HttpStatus.OK);
    }

    return new ResponseEntity<>("Could not complete request",
                                HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/transfers")
  public ResponseEntity<?> userToUserTransferHandler(
    @RequestBody UserToUserDTO userToUser) {
    if (userToUser == null) {
      return new ResponseEntity<>("User information was null",
                                  HttpStatus.BAD_REQUEST);
    }

    User from = userServices.getUserByEmail(userToUser.getFromEmail());
    User to = userServices.getUserByEmail(userToUser.getToEmail());
    Account fromAcct =
      accountServices.getUserAccountById(from, userToUser.getFromAcct());
    Account toAcct =
      accountServices.getUserAccountById(to, userToUser.getToAcct());

    if (from != null && to != null && fromAcct != null && toAcct != null) {
      Transaction complete =
        transactionServices.userToUserTransfer(from, fromAcct, to, toAcct,
                                               userToUser.getAmount());

      return new ResponseEntity<>(complete, HttpStatus.CREATED);
    }

    return new ResponseEntity<>("Could not complete user to user transfer",
                                HttpStatus.BAD_REQUEST);
  }
}