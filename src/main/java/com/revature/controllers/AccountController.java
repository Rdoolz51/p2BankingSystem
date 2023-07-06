package com.revature.controllers;

import com.revature.models.Account;
import com.revature.services.AccountServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountServices accountServices;

    public AccountController(AccountServices accountServices) {
        this.accountServices = accountServices;
    }
    @GetMapping("/{accountID}")
    public Account getAccountByID(@PathVariable int accountID) {
        return accountServices.getAccountByID(accountID);
    }

    @GetMapping("/users/{userID}")
    public List<Account> getAccountsByUserID(@PathVariable int userID) {
        return accountServices.getAccountsByUserID(userID);
    }

    @PostMapping("/create")
    public void createAccount(@RequestBody Account account) {
        accountServices.addAccount(account);
    }

    @PutMapping("/update")
    public void updateAccount(@RequestBody Account account) {
        accountServices.updateAccount(account);
    }

    @DeleteMapping("/delete")
    public void deleteAccount(@RequestBody Account account) {
        accountServices.deleteAccount(account);
    }


}
