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
        return userDAO.findByUserId(userID);
    }

    public User getUserByEmail(String email)
    {
        return userDAO.findByEmail(email);
    }

    public void addUser(User user) {
        userDAO.save(user);
    }

    public boolean updateUser(User user)
    {
        userDAO.save(user);
        return false;
    }

    public void deleteUser(User user)
    {
        userDAO.save(user);
    }

    public List<User> getAllUsers()
    {
        return userDAO.findAll();
    }

    public void createAccount(User user, Account accountType) {
        Account account = new Account(user, accountType);
        accountDAO.save(account);
    }

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public void logout() {
        // Perform any necessary logout logic
    }

    public List<Account> getAllAccounts(User user)
    {
        return accountDAO.findByUserId(user.getId());
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

        senderAccount.setBalance(senderBalance - amount);
        receiverAccount.setBalance(receiverBalance + amount);

        accountDAO.save(senderAccount);
        accountDAO.save(receiverAccount);
    }

}

