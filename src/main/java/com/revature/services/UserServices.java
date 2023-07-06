package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Account;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    private final UserDAO userDAO;
    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;
    @Autowired
    public UserServices(UserDAO userDAO, AccountDAO accountDAO, TransactionDAO transactionDAO) {
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
    }
    public User getUserByID(int userID) {
        return userDAO.getUserByID(userID);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void createAccount(User user, Account accountType) {
        Account account = new Account(user, accountType);
        accountDAO.addAccount(account);
    }

    public List<Account> getAllAccounts(User user) {
        return accountDAO.getAccountsByUserID(user.getUserID());
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

