package com.revature.controllers;

import com.revature.dtos.LoginDTO;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.TransactionServices;
import com.revature.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;
    private final AccountServices accountServices;
    private final TransactionServices transactionServices;

    @Autowired
    public UserController(UserServices userServices, AccountServices accountServices, TransactionServices transactionServices)
    {
        this.userServices = userServices;
        this.accountServices = accountServices;
        this.transactionServices = transactionServices;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) {

        User authenticatedUser = userServices.login(LoginDTO.getUsername(), loginDTO.getPassword());

        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        userServices.logout();
        return ResponseEntity.ok("Logged out successfully");
    }
    @GetMapping("/{userID}")
    public User getUserByID(@PathVariable int id) {
        return userServices.getUserByID(id);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userServices.getUserByEmail(email);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        userServices.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") int id, @RequestBody User user)
    {
        if(user != null) {
            user.setId(id);
            if(userServices.updateUser(user)) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
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

    //had hard time doing this last logic it wont work
    /*@PostMapping("/accounts/transfer")
    public void transferMoney(@RequestBody Account account)
    {
        Account senderAccount = accountServices.getAccountByID(transferRequest.getSenderAccountID());
        Account receiverAccount = accountServices.getAccountByID(transferRequest.getReceiverAccountID());
        double amount = transferRequest.getAmount();
        accountServices.transferMoney(senderAccount, receiverAccount, amount);
    }

     */




}
