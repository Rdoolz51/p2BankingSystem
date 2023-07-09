package com.revature.controllers;

import com.revature.dtos.AccountDTO;
import com.revature.dtos.AccountTransactionDTO;
import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("mybank")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {
  private final String INVALID = "User token is either invalid or expired";
  private final String INVALID_ID = "Provided ID or TYPE was not valid";
  private final UserServices userServices;
  private final AccountServices accountServices;

  @Autowired
  public UserController(UserServices userServices,
                        AccountServices accountServices) {
    this.userServices = userServices;
    this.accountServices = accountServices;
  }

  /*@GetMapping("/{cid}")
  public ResponseEntity<?> getUserByIdHandler(
    @RequestHeader("Authorization") String token,
    @PathVariable("cid") int cid) {
    User user = userServices.checkUserToken(token);

    if (user != null && user.getId() == cid) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID,
                                HttpStatus.FORBIDDEN);
  }*/

  @GetMapping
  public ResponseEntity<?> getUserByJwtHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @GetMapping("/accounts")
  public ResponseEntity<?> getUserAccountsHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(accountServices.getUserAccounts(user),
                                  HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @GetMapping("/accounts/{aid}")
  public ResponseEntity<?> getUserAccountByIdHandler(
    @RequestHeader("Authorization") String token,
    @PathVariable("aid") int aid) {
    User user = userServices.checkUserToken(token);

    if (aid <= 0) {
      return new ResponseEntity<>(INVALID_ID, HttpStatus.BAD_REQUEST);
    }
    if (user != null) {
      return new ResponseEntity<>(accountServices.getUserAccountById(user, aid),
                                  HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @GetMapping("/accounts/{type}")
  public ResponseEntity<?> getUserAccountsByTypeHandler(
    @RequestHeader("Authorization") String token,
    @PathVariable("type") String type) {
    User user = userServices.checkUserToken(token);
    AccountType retrieved = accountServices.getAccountTypeByName(type);

    if (retrieved == null) {
      return new ResponseEntity<>(INVALID_ID, HttpStatus.BAD_REQUEST);
    }

    if (user != null) {
      return new ResponseEntity<>(
        accountServices.getUserAccountsByType(user, retrieved),
        HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }


  @PostMapping("/accounts")
  public ResponseEntity<?> createNewAccountHandler(
    @RequestHeader("Authorization") String token,
    @RequestBody AccountDTO account) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      Account newAccount = accountServices.registerAccount(
        new Account(
          BigDecimal.ZERO,
          account.getType(),
          account.getPin(),
          user
        )
      );

      if (newAccount != null && newAccount.getAccountID() > 0) {
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
      }
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @PutMapping
  public ResponseEntity<?> updateUserHandler(
    @RequestHeader("Authorization") String token,
    @RequestBody User updated) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      User complete = userServices.updateUser(user, updated);

      if (complete != null) {
        return new ResponseEntity<>(complete, HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Failed to update: check user ID",
                                    HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @PutMapping("/deposit")
  public ResponseEntity<?> depositHandler(
    @RequestHeader("Authorization") String token,
    @RequestBody AccountTransactionDTO acctTrans) {
    User user = userServices.checkUserToken(token);

    if (acctTrans == null) {
      return new ResponseEntity<>("Account information was null",
                                  HttpStatus.BAD_REQUEST);
    }

    if (user != null) {
      Account account =
        accountServices.getUserAccountById(user, acctTrans.getAccountId());

      return new ResponseEntity<>(accountServices.deposit(account, acctTrans.getAmount()),
                                  HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @PutMapping("/withdrawal")
  public ResponseEntity<?> withdrawalHandler(
    @RequestHeader("Authorization") String token,
    @RequestBody AccountTransactionDTO acctTrans) {
    User user = userServices.checkUserToken(token);

    if (acctTrans == null) {
      return new ResponseEntity<>("Account information was null",
                                  HttpStatus.BAD_REQUEST);
    }

    if (user != null) {
      Account account =
        accountServices.getUserAccountById(user, acctTrans.getAccountId());

      return new ResponseEntity<>(accountServices.withdrawal(account, acctTrans.getAmount()),
                                  HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }
}
