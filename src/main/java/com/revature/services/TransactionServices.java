package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionDAO;
import com.revature.daos.TransactionTypeDAO;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional(rollbackOn = SQLException.class)
public class TransactionServices {

  private final TransactionDAO transactionDAO;
  private final TransactionTypeDAO transactionTypeDAO;

  private final AccountDAO accountDAO;

  @Autowired
  public TransactionServices(TransactionDAO transactionDAO,
                             TransactionTypeDAO transactionTypeDAO,
                             AccountDAO accountDAO) {
    this.transactionDAO = transactionDAO;
    this.transactionTypeDAO = transactionTypeDAO;
    this.accountDAO = accountDAO;
  }

  /**
   * Records a debit to the customer's credit card.
   *
   * @param user              the user to be debited
   * @param receiverAccountId the account id who will be credited
   * @param amount            the amount of the transaction
   * @return transaction object if complete; otherwise null
   */
  public Transaction debitUserCC(User user, int receiverAccountId,
                                 String amount) {
    if (user != null && receiverAccountId > 0 && !amount.isEmpty()) {
      BigDecimal bdAmount = new BigDecimal(amount);
      Account customerAcct = accountDAO.findByUser_id(user.getId());

      if (bdAmount != null && customerAcct != null &&
          bdAmount.compareTo(BigDecimal.ZERO) == 1) {
        BigDecimal balance = new BigDecimal(customerAcct.getBalance());

        customerAcct.setBalance(balance.subtract(bdAmount).toString());

        Transaction complete = new Transaction();

        complete.setAmount(amount);
        complete.setType(transactionTypeDAO.findByType("Debit"));
        complete.setTransactionDate(LocalDateTime.now());
        complete.setUserAccount(customerAcct);
        complete.setTransactionAcctId(receiverAccountId);

        transactionDAO.save(complete);
        log.info("Completed CC debit transaction between customer ID: " +
                 user.getId() + " and receiver ID: " + receiverAccountId);

        return complete;
      }
    }

    log.warn("Could not complete CC debit transaction");
    return null;
  }

  /**
   * Records a credit to the customer's CC.
   *
   * @param user the user to be credited
   * @param senderAccountId the account id to be debited
   * @param amount the amount of the transaction
   * @return Transaction object if successful; otherwise null
   */
  public Transaction creditUserCC(User user, int senderAccountId,
                                 String amount) {
    if (user != null && senderAccountId > 0 && !amount.isEmpty()) {
      BigDecimal bdAmount = new BigDecimal(amount);
      Account customerAcct = accountDAO.findByUser_id(user.getId());

      if (bdAmount != null && customerAcct != null &&
          bdAmount.compareTo(BigDecimal.ZERO) == 1) {
        BigDecimal balance = new BigDecimal(customerAcct.getBalance());

        customerAcct.setBalance(balance.add(bdAmount).toString());

        Transaction complete = new Transaction();

        complete.setAmount(amount);
        complete.setType(transactionTypeDAO.findByType("Credit"));
        complete.setTransactionDate(LocalDateTime.now());
        complete.setUserAccount(customerAcct);
        complete.setTransactionAcctId(senderAccountId);

        transactionDAO.save(complete);
        log.info("Completed CC credit transaction between customer ID: " +
                 user.getId() + " and receiver ID: " + senderAccountId);

        return complete;
      }
    }

    log.warn("Could not complete CC credit transaction");
    return null;
  }
}
