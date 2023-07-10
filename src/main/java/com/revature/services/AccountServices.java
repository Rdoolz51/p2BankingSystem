package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountTypeDAO;
import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServices {
  private final AccountDAO accountDAO;
  private final AccountTypeDAO accountTypeDAO;

  @Autowired
  public AccountServices(AccountDAO accountDAO, AccountTypeDAO accountTypeDAO) {
    this.accountDAO = accountDAO;
    this.accountTypeDAO = accountTypeDAO;
  }

  public Account registerAccount(Account account) {
    if (account == null) {
      log.warn("Null user object was received");
      throw new NullPointerException("Account object was null");
    }

    Account newAccount = accountDAO.save(account);

    if (newAccount != null && newAccount.getAccountID() > 0) {
      log.info("Account created for " + newAccount.getUser().getEmail());
      return newAccount;
    }

    log.warn("Account could not be created");
    return null;
  }

  public List<Account> getUserAccounts(User user) {
    log.info("Retrieved all accounts associated with user ID: " + user.getId());
    return accountDAO.findByUser(user);
  }

  public Account getUserAccountById(User user, int aid) {
    if (user != null && accountDAO.existsById(aid)) {
      Optional<Account> retrieved = accountDAO.findById(aid);

      if (retrieved.isPresent()) {
        log.info("Retrieved user account with ID: " + aid);
        return retrieved.get();
      }
    }

    log.warn("Failed to retrieve user account by ID: " + aid);
    return null;
  }

  public List<Account> getUserAccountsByType(User user, AccountType type) {
    if (user != null && type != null) {
      log.info("Retrieved all user accounts by type: " + type.getType());
      return accountDAO.findByType(type);
    }
    log.warn("Failed to retrieve all user accounts by type: " + type.getType());
    return null;
  }

  public AccountType getAccountTypeByName(String type) {
    if (type != null && accountTypeDAO.existsByType(type)) {
      log.info("Account type retrieved successfully");
      return accountTypeDAO.findByType(type);
    }

    log.warn("Could not retrieve account type: " + type);
    return null;
  }

  @Transactional(rollbackOn = SQLException.class)
  public Account deposit(Account account, String amount, User user) {
    if (account != null && amount != null &&
        user.getId() == account.getUser().getId()) {
      BigDecimal balance = new BigDecimal(account.getBalance());
      BigDecimal bdAmount = new BigDecimal(amount);

      account.setBalance(balance.add(bdAmount).toString());

      accountDAO.save(account);

      log.info("Deposit completed for account: " + account.getAccountID());
      return account;
    }

    log.warn("Deposit failed for account: " + account.getAccountID());
    return null;
  }

  @Transactional(rollbackOn = SQLException.class)
  public Account withdrawal(Account account, String amount, User user) {
    if (account != null && amount != null &&
        user.getId() == account.getUser().getId()) {
      BigDecimal balance = new BigDecimal(account.getBalance());
      BigDecimal bdAmount = new BigDecimal(amount);

      account.setBalance(balance.subtract(bdAmount).toString());

      accountDAO.save(account);

      log.info("Withdrawal completed for account: " + account.getAccountID());
      return account;
    }

    log.warn("Withdrawal failed for account: " + account.getAccountID());
    return null;
  }

  /**
   * Money transfer between a single user's accounts.
   *
   * @param from the account the money should be taken from
   * @param to the account the money should be added to
   * @param amount the amount of money to be transferred
   * @param user the user who owns the accounts
   * @return List containing the two accounts; null on failure
   */
  @Transactional(rollbackOn = SQLException.class)
  public List<Account> transfer(Account from, Account to, String amount,
                                User user) {
    if (from != null && to != null && amount != null) {
      if (from.getUser().getId() == user.getId() &&
          to.getUser().getId() == user.getId()) {
        BigDecimal fromBalance = new BigDecimal(from.getBalance());
        BigDecimal toBalance = new BigDecimal(to.getBalance());
        BigDecimal bdAmount = new BigDecimal(amount);

        from.setBalance(fromBalance.subtract(bdAmount).toString());
        to.setBalance(toBalance.add(bdAmount).toString());

        Account fromSaved = accountDAO.save(from);
        Account toSaved = accountDAO.save(to);

        log.info(
          "Transfer from account: " + from.getAccountID() + " to account: " +
          to.getAccountID() + " completed");

        List<Account> toReturn = new ArrayList<>();

        toReturn.add(from);
        toReturn.add(to);

        return toReturn;
      }
    }

    log.warn("Transfer from failed");
    return null;
  }
}
