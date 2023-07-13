package com.revature.services;

import com.revature.daos.*;
import com.revature.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class AccountServices {
  private final AccountDAO accountDAO;
  private final AccountTypeDAO accountTypeDAO;
  private final LoanDAO loanDAO;
  private final CreditCardDAO creditCardDAO;
  private final TransactionTypeDAO transactionTypeDAO;
  private final TransactionDAO transactionDAO;

  @Autowired
  public AccountServices(AccountDAO accountDAO, AccountTypeDAO accountTypeDAO,
                         LoanDAO loanDAO, CreditCardDAO creditCardDAO,
                         TransactionTypeDAO transactionTypeDAO,
                         TransactionDAO transactionDAO) {
    this.accountDAO = accountDAO;
    this.accountTypeDAO = accountTypeDAO;
    this.loanDAO = loanDAO;
    this.creditCardDAO = creditCardDAO;
    this.transactionTypeDAO = transactionTypeDAO;
    this.transactionDAO = transactionDAO;
  }

  /**
   *
   * @param account
   * @return
   */
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

  /**
   *
   * @param user
   * @return
   */
  public List<Account> getUserAccounts(User user) {
    log.info("Retrieved all accounts associated with user ID: " + user.getId());
    return accountDAO.findByUser(user);
  }

  /**
   *
   * @param user
   * @param aid
   * @return
   */
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

  public Map<Integer, List<String>> getAccountSummary(User user) {
    if (user != null) {
      List<Account> accounts = accountDAO.findByUser(user);
      Map<Integer, List<String>> summary = new HashMap<>();

      if (accounts != null) {
        for (Account a : accounts) {
          List<String> temp = new ArrayList<>();
          temp.add(a.getType().getType());
          temp.add(a.getFakeAccountId());
          temp.add(String.valueOf(a.getAccountID()));

          summary.put(a.getAccountID(), temp);
        }

        log.info("Retrieved user accounts by email: " + user.getEmail());
        return summary;
      }
    }

    log.warn("Could not get user account IDs by email");
    return null;
  }

  public List<AccountType> getAccountTypesByEmail(User user) {
    if (user != null) {
      List<Account> accounts = accountDAO.findByUser(user);
      List<AccountType> types = new ArrayList<>();

      if (accounts != null) {
        for (Account a : accounts) {
          types.add(a.getType());
        }

        log.info("Retrieved account types by email: " + user.getEmail());
        return types;
      }
    }

    log.warn("Could not get user account types by email");
    return null;
  }

  /**
   *
   * @param user
   * @param type
   * @return
   */
  public List<Account> getUserAccountsByType(User user, AccountType type) {
    if (user != null && type != null) {
      log.info("Retrieved all user accounts by type: " + type.getType());
      return accountDAO.findByType(type);
    }
    log.warn("Failed to retrieve all user accounts by type: " + type.getType());
    return null;
  }

  /**
   *
   * @param type
   * @return
   */
  public AccountType getAccountTypeByName(String type) {
    if (type != null && accountTypeDAO.existsByType(type)) {
      log.info("Account type retrieved successfully");
      return accountTypeDAO.findByType(type);
    }

    log.warn("Could not retrieve account type: " + type);
    return null;
  }

  /**
   *
   * @param account
   * @param amount
   * @param user
   * @return
   */
  @Transactional(rollbackOn = SQLException.class)
  public Account deposit(Account account, String amount, User user) {
    if (account != null && amount != null &&
        user.getId() == account.getUser().getId()) {
      BigDecimal balance = new BigDecimal(account.getBalance());
      BigDecimal bdAmount = new BigDecimal(amount);

      account.setBalance(balance.add(bdAmount).toString());

      accountDAO.save(account);

      Transaction transaction = new Transaction();

      transaction.setType(transactionTypeDAO.findByType("Deposit"));
      transaction.setAmount(amount);
      transaction.setUserAccount(account);
      transaction.setTransactionDate(LocalDateTime.now());
      transaction.setTransactionAcctId(account.getAccountID());

      transactionDAO.save(transaction);

      log.info("Deposit completed for account: " + account.getAccountID());
      return account;
    }

    log.warn("Deposit failed for account: " + account.getAccountID());
    return null;
  }

  /**
   *
   * @param account
   * @param amount
   * @param user
   * @return
   */
  @Transactional(rollbackOn = SQLException.class)
  public Account withdrawal(Account account, String amount, User user) {
    if (account != null && amount != null &&
        user.getId() == account.getUser().getId()) {
      BigDecimal balance = new BigDecimal(account.getBalance());
      BigDecimal bdAmount = new BigDecimal(amount);

      account.setBalance(balance.subtract(bdAmount).toString());

      accountDAO.save(account);

      Transaction transaction = new Transaction();

      transaction.setType(transactionTypeDAO.findByType("Withdrawal"));
      transaction.setAmount(amount);
      transaction.setUserAccount(account);
      transaction.setTransactionDate(LocalDateTime.now());
      transaction.setTransactionAcctId(account.getAccountID());

      transactionDAO.save(transaction);

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

        Transaction transaction = new Transaction();

        transaction.setType(transactionTypeDAO.findByType("Transfer"));
        transaction.setAmount(amount);
        transaction.setUserAccount(from);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionAcctId(to.getAccountID());

        transactionDAO.save(transaction);

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

  public Loan loanApplication(Loan loan, User user) {
    if (user != null && loan != null && loan.getUser().getId() == user.getId()) {
      Loan complete = loanDAO.save(loan);

      log.info("Loan application submitted");
      return complete;
    }

    log.warn("Loan application could not be completed");
    return null;
  }

  public CreditCard creditCardApplication(CreditCard creditCard, User user) {
    if (user != null && creditCard != null && creditCard.getUser().getId() == user.getId()) {
      CreditCard complete = creditCardDAO.save(creditCard);

      log.info("Credit card application submitted");
      return complete;
    }

    log.warn("Credit card application could not be completed");
    return null;
  }

  public List<CreditCard> getAllUserCards(User user) {
    return creditCardDAO.findByUser_Id(user.getId());
  }

  public List<Loan> getAllUserLoans(User user) {
    return loanDAO.findByUser_Id(user.getId());
  }
}
