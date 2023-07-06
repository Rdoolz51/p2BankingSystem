package com.revature.services;

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
    public Account getAccountByID(int accountID) {
        return accountDAO.getAccountByID(accountID);
    }

    public List<Account> getAccountsByUserID(int userID) {
        return accountDAO.getAccountsByUserID(userID);
    }

    public void addAccount(Account account) {
        accountDAO.addAccount(account);
    }

    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    public void deleteAccount(Account account) {
        accountDAO.deleteAccount(account);
    }

    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    public void addMoneyToAccount(Account account, double amount) {
        double currentBalance = account.getBalance();
        account.setBalance(currentBalance + amount);
        accountDAO.updateAccount(account);
    }

    public void removeMoneyFromAccount(Account account, double amount) {
        double currentBalance = account.getBalance();
        account.setBalance(currentBalance - amount);
        accountDAO.updateAccount(account);
    }

    public void transferMoney(Account senderAccount, Account receiverAccount, double amount) {
        double senderBalance = senderAccount.getBalance();
        double receiverBalance = receiverAccount.getBalance();

        senderAccount.setBalance(senderBalance - amount);
        receiverAccount.setBalance(receiverBalance + amount);

        accountDAO.updateAccount(senderAccount);
        accountDAO.updateAccount(receiverAccount);
    }
}
