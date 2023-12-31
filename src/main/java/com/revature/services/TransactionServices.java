package com.revature.services;

import com.revature.daos.*;
import com.revature.models.*;
import com.revature.util.ValidateCC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional(rollbackOn = SQLException.class)
public class TransactionServices {

  private final TransactionDAO transactionDAO;
  private final TransactionTypeDAO transactionTypeDAO;
  private final CreditCardDAO creditCardDAO;
  private final AccountDAO accountDAO;
  private final UserDAO userDAO;

  @Autowired
  public TransactionServices(TransactionDAO transactionDAO,
                             TransactionTypeDAO transactionTypeDAO,
                             CreditCardDAO creditCardDAO,
                             AccountDAO accountDAO, UserDAO userDAO) {
    this.transactionDAO = transactionDAO;
    this.transactionTypeDAO = transactionTypeDAO;
    this.creditCardDAO = creditCardDAO;
    this.accountDAO = accountDAO;
    this.userDAO = userDAO;
  }

  /**
   * Records a credit to the customer's CC.
   *
   * @param creditCard      the cc to be credited
   * @param senderAccountId the account id to be debited
   * @param amount          the amount of the transaction
   * @return Transaction object if successful; otherwise null
   */
  public Transaction creditUserCC(CreditCard creditCard,
                                  int senderAccountId, String amount) {
    if (creditCard != null && senderAccountId > 0 &&
        !amount.isEmpty()) {
      if (creditCardDAO.existsById(creditCard.getCreditID()) &&
          ValidateCC.validateCard(creditCard.getCardNumber())) {
        int monthNow = LocalDate.now().getMonthValue();
        int yearNow = LocalDate.now().getYear() - 2000;
        int month =
          Integer.parseInt(creditCard.getCardExpiration().substring(0, 2));
        int year =
          Integer.parseInt(creditCard.getCardExpiration().substring(3, 5));
        BigDecimal bdAmount = new BigDecimal(amount);
        BigDecimal balance = new BigDecimal(creditCard.getBalance());

        if (((year == yearNow) && (month >= monthNow)) || year > yearNow) {
          if (bdAmount != null && balance != null &&
              bdAmount.compareTo(BigDecimal.ZERO) == 1) {
            BigDecimal newBalance = balance.subtract(bdAmount);
            Transaction complete = new Transaction();

            complete.setAmount(amount);
            complete.setType(transactionTypeDAO.findByType("Credit"));
            complete.setTransactionDate(LocalDateTime.now());
            complete.setUserAccount(
              accountDAO.findByUser_id(creditCard.getUser().getId()));
            complete.setTransactionAcctId(senderAccountId);

            if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {

              creditCard.setBalance(newBalance.toString());
              creditCardDAO.save(creditCard);

            } else {
              creditCard.setBalance("0");
              creditCardDAO.save(creditCard);
            }

            transactionDAO.save(complete);
            log.info("Completed CC debit transaction between customer ID: " +
                     creditCard.getUser().getId() + " and receiver ID: " +
                     senderAccountId);

            return complete;
          }
        } else {
          log.warn("Card is expired");
          return null;
        }
      } else {
        log.warn("CC account does not exist or was not a valid card number");
        return null;
      }
    }

    log.warn("Could not complete CC debit transaction");
    return null;
  }

  /**
   * Records a debit to the customer's CC.
   *
   * @param creditCard        the cc to be debited
   * @param receiverAccountId the account id to be credited
   * @param amount            the amount of the transaction
   * @return Transaction object if successful; otherwise null
   */
  public Transaction debitUserCC(CreditCard creditCard,
                                 int receiverAccountId, String amount) {
    if (creditCard != null && receiverAccountId > 0 &&
        !amount.isEmpty()) {
      if (creditCardDAO.existsById(creditCard.getCreditID()) &&
          ValidateCC.validateCard(creditCard.getCardNumber())) {
        int monthNow = LocalDate.now().getMonthValue();
        int yearNow = LocalDate.now().getYear() - 2000;
        int month =
          Integer.parseInt(creditCard.getCardExpiration().substring(0, 2));
        int year =
          Integer.parseInt(creditCard.getCardExpiration().substring(3, 5));
        BigDecimal bdAmount = new BigDecimal(amount);
        BigDecimal balance = new BigDecimal(creditCard.getBalance());

        if (((year == yearNow) && (month >= monthNow)) || year > yearNow) {
          if (bdAmount != null && balance != null &&
              bdAmount.compareTo(BigDecimal.ZERO) == 1) {
            BigDecimal newBalance = balance.add(bdAmount);
            Transaction complete = new Transaction();

            complete.setAmount(amount);
            complete.setType(transactionTypeDAO.findByType("Debit"));
            complete.setTransactionDate(LocalDateTime.now());
            complete.setUserAccount(
              accountDAO.findByUser_id(creditCard.getUser().getId()));
            complete.setTransactionAcctId(receiverAccountId);

            if (
              newBalance.compareTo(
                new BigDecimal(creditCard.getCreditLimit())) <=
              0) {

              creditCard.setBalance(newBalance.toString());
              creditCardDAO.save(creditCard);

            } else {
              creditCard.setBalance(creditCard.getCreditLimit());
              creditCardDAO.save(creditCard);
            }

            transactionDAO.save(complete);
            log.info("Completed CC credit transaction between customer ID: " +
                     creditCard.getUser().getId() + " and receiver ID: " +
                     receiverAccountId);

            return complete;
          }
        } else {
          log.warn("Card is expired");
          return null;
        }
      } else {
        log.warn("CC account does not exist or was not a valid card number");
        return null;
      }
    }

    log.warn("Could not complete CC debit transaction");
    return null;
  }

  public Transaction userToUserTransfer(User from, Account fromAcct, User to,
                                    Account toAcct, String amount) {
    if (from != null && to != null && fromAcct != null && toAcct != null &&
        !amount.isEmpty()) {
      BigDecimal dbAmount = new BigDecimal(amount);

      if (userDAO.existsByEmail(from.getEmail()) &&
          userDAO.existsByEmail(to.getEmail()) &&
          accountDAO.existsById(fromAcct.getAccountID()) &&
          accountDAO.existsById(toAcct.getAccountID()) &&
          from.getId() == fromAcct.getUser().getId() &&
          to.getId() == toAcct.getUser().getId()) {
        Transaction transaction = new Transaction();
        BigDecimal fromBalance = new BigDecimal(fromAcct.getBalance());
        BigDecimal fromAdjusted = fromBalance.subtract(dbAmount);
        BigDecimal toBalance = new BigDecimal(toAcct.getBalance());

        if (fromAdjusted.compareTo(BigDecimal.ZERO) >= 0) {
          fromAcct.setBalance(fromBalance.subtract(dbAmount).toString());
          accountDAO.save(fromAcct);
        } else {
          log.warn("Insufficient funds to complete transfer");
          return null;
        }

        toAcct.setBalance(toBalance.add(dbAmount).toString());
        accountDAO.save(toAcct);

        log.info(
          "Transfer between " + from.getId() + ":" + fromAcct.getAccountID() +
          " and " + to.getId() + ":" + toAcct.getAccountID() + " completed");

        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setUserAccount(fromAcct);
        transaction.setTransactionAcctId(toAcct.getAccountID());
        transaction.setAmount(amount);
        transaction.setType(transactionTypeDAO.findByType("Transfer"));

        transactionDAO.save(transaction);
        log.info("Transaction saved");

        return transaction;
      }
    }

    log.warn("Transaction could not be completed");
    return null;
  }
}
