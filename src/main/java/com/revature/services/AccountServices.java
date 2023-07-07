package com.revature.services;

import com.revature.Exceptions.InsufficientFundsException;
import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServices {
    private final UserDAO userDAO;
    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;

    @Autowired
    public AccountServices(UserDAO userDAO, AccountDAO accountDAO, TransactionDAO transactionDAO) {
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
    }
    public Account getAccountByID(int accountID)
    {
        return accountDAO.findById(accountID).orElse(null);
    }

    public List<Account> getAccountsByUserID(int userID)
    {
        return accountDAO.findByUserId(userID);
    }

    public void addAccount(Account account)
    {
        accountDAO.save(account);
    }

    public void updateAccount(Account account) {
        accountDAO.save(account);
    }

    public void deleteAccount(Account account)
    {
        accountDAO.save(account);
    }

    public List<Account> getAllAccounts()
    {
        return accountDAO.findAll();
    }

    public void addMoneyToAccount(Account account, double amount) {
        double currentBalance = account.getBalance();
        account.setBalance(currentBalance + amount);
        accountDAO.save(account);
    }

    public void removeMoneyFromAccount(Account account, double amount) {
        double currentBalance = account.getBalance();
        account.setBalance(currentBalance - amount);
        accountDAO.save(account);
    }

    public void transferMoney(Account senderAccount, Account receiverAccount, double amount) {
        double senderBalance = senderAccount.getBalance();
        double receiverBalance = receiverAccount.getBalance();

        // Check if sender has sufficient funds
        if (senderBalance < amount) {
            throw new InsufficientFundsException("Insufficient funds in sender account.");
        }

        // Update sender and receiver balances
        senderAccount.setBalance(senderBalance - amount);
        receiverAccount.setBalance(receiverBalance + amount);

        // Save modified sender and receiver accounts
        accountDAO.save(senderAccount);
        accountDAO.save(receiverAccount);
    }
}
