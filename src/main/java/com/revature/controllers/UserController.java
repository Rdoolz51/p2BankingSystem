package com.revature.controllers;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;
    private final AccountServices accountServices;

    @Autowired
    public UserController(UserServices userServices, AccountServices accountServices)
    {
        this.userServices = userServices;
        this.accountServices = accountServices;
    }
    @GetMapping("/{userID}")
    public User getUserByID(@PathVariable int userID) {
        return userServices.getUserByID(userID);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userServices.getUserByEmail(email);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        userServices.addUser(user);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        userServices.updateUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody User user) {
        userServices.deleteUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userServices.getAllUsers();
    }

    @PostMapping("/{userID}/accounts/create")
    public void createAccount(@PathVariable int userID, @RequestBody Account accountType) {
        User user = userServices.getUserByID(userID);
        userServices.createAccount(user, accountType);
    }

    @GetMapping("/{userID}/accounts")
    public List<Account> getUserAccounts(@PathVariable int userID) {
        User user = userServices.getUserByID(userID);
        return userServices.getAllAccounts(user);
    }

    @PostMapping("/accounts/{accountID}/add-money")
    public void addMoneyToAccount(@PathVariable int accountID, @RequestBody double amount) {
        Account account = accountServices.getAccountByID(accountID);
        userServices.addMoneyToAccount(account, amount);
    }

    @PostMapping("/accounts/{accountID}/remove-money")
    public void removeMoneyFromAccount(@PathVariable int accountID, @RequestBody double amount) {
        Account account = accountServices.getAccountByID(accountID);
        userServices.removeMoneyFromAccount(account, amount);
    }

    /*@PostMapping("/accounts/transfer")
    public void transferMoney(@RequestBody TransferRequest transferRequest)
    {
        Account senderAccount = accountServices.getAccountByID(transferRequest.getSenderAccountID());
        Account receiverAccount = accountServices.getAccountByID(transferRequest.getReceiverAccountID());
        double amount = transferRequest.getAmount();
        userServices.transferMoney(senderAccount, receiverAccount, amount);
    }

     */


}
