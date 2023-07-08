package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountTypeDAO;
import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    return accountDAO.findByEmail(user.getEmail());
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
}
