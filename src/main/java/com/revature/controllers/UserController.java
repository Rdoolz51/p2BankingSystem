package com.revature.controllers;

import com.revature.daos.StatusDAO;
import com.revature.dtos.*;
import com.revature.models.*;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("mybank")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:3000"})
public class UserController {
  private final String INVALID = "User token is either invalid or expired";
  private final String INVALID_ID = "Provided ID or TYPE was not valid";
  private final UserServices userServices;
  private final AccountServices accountServices;
  private final StatusDAO statusDAO;

  @Autowired
  public UserController(UserServices userServices,
                        AccountServices accountServices, StatusDAO statusDAO) {
    this.userServices = userServices;
    this.accountServices = accountServices;
    this.statusDAO = statusDAO;
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
      return new ResponseEntity<>(userServices.getUserByEmail(user.getEmail()),
                                  HttpStatus.OK);
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
          "",
          account.getType(),
          account.getPin(),
          user,
          account.getFakeAccountId()
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

      Account complete =
        accountServices.deposit(account, acctTrans.getAmount(), user);

      if (complete != null) {
        return new ResponseEntity<>(complete, HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Deposit could not be completed",
                                    HttpStatus.BAD_REQUEST);
      }
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

      Account complete =
        accountServices.withdrawal(account, acctTrans.getAmount(), user);

      if (complete != null) {
        return new ResponseEntity<>(complete, HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Withdrawal could not be completed",
                                    HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @PutMapping("/transfers")
  public ResponseEntity<?> transferHandler(
    @RequestHeader("Authorization") String token,
    @RequestBody AccountTransferDTO atDTO) {
    User user = userServices.checkUserToken(token);

    if (atDTO == null) {
      return new ResponseEntity<>("Account transfer information was null",
                                  HttpStatus.BAD_REQUEST);
    }

    if (user != null) {
      Account from =
        accountServices.getUserAccountById(user, atDTO.getFromAccountId());
      Account to =
        accountServices.getUserAccountById(user, atDTO.getToAccountId());
      BigDecimal fromBalance = new BigDecimal(from.getBalance());
      BigDecimal amount = new BigDecimal(atDTO.getAmount());
      List<Account> complete = null;

      if (fromBalance.compareTo(amount) >= 0) {
        complete = accountServices.transfer(from, to, amount.toString(), user);
      } else {
        return new ResponseEntity<>("Insufficient funds to complete transfer",
                                    HttpStatus.BAD_REQUEST);
      }

      if (complete != null) {
        return new ResponseEntity<>(complete, HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Transfer could not be completed",
                                    HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  /**
   * @param token
   * @param app
   * @return
   */
  @PostMapping("/loan-app")
  public ResponseEntity<?> loanApplicationHandler(
    @RequestHeader("Authorization") String token,
    @RequestBody LoanDTO app) {
    User user = userServices.checkUserToken(token);

    if (app == null) {
      return new ResponseEntity("Loan application was null",
                                HttpStatus.BAD_REQUEST);
    }

    if (user != null) {

      Loan loan = new Loan();

      loan.setUser(user);
      loan.setLoanAmount(app.getAmount());
      loan.setLoanBalance("0");
      loan.setType(app.getType());
      loan.setInterestRate(app.getInterestRate());
      loan.setStatus(statusDAO.findByStatus("Pending"));

      try {
        Loan complete = accountServices.loanApplication(loan, user);

        return new ResponseEntity(complete, HttpStatus.CREATED);
      } catch (ConstraintViolationException cve) {
        return new ResponseEntity("Application data was incomplete",
                                  HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity(INVALID, HttpStatus.FORBIDDEN);
  }

  /**
   * @param token
   * @param app
   * @return
   */
  @PostMapping("/cc-app")
  public ResponseEntity<?> creditCardApplicationHandler(
    @RequestHeader("Authorization") String token,
    @RequestBody CreditDTO app) {
    User user = userServices.checkUserToken(token);

    if (app == null) {
      return new ResponseEntity("Credit card application was null",
                                HttpStatus.BAD_REQUEST);
    }

    if (user != null) {

      CreditCard creditCard = new CreditCard();

      creditCard.setUser(user);
      creditCard.setCreditLimit(app.getCreditLimit());
      creditCard.setBalance("0");
      creditCard.setInterestRate(app.getInterestRate());
      creditCard.setAnnualFee(app.getAnnualFee());
      creditCard.setStatus(statusDAO.findByStatus("Pending"));

      try {
        CreditCard complete =
          accountServices.creditCardApplication(creditCard, user);

        return new ResponseEntity(complete, HttpStatus.CREATED);
      } catch (ConstraintViolationException cve) {
        return new ResponseEntity("Application data was incomplete",
                                  HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity(INVALID, HttpStatus.FORBIDDEN);
  }

  @GetMapping("cards")
  public ResponseEntity<?> getUserCardsHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(accountServices.getAllUserCards(user),
                                  HttpStatus.OK);
    }

    return new ResponseEntity(INVALID, HttpStatus.FORBIDDEN);
  }

  @GetMapping("loans")
  public ResponseEntity<?> getUserLoansHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(accountServices.getAllUserLoans(user),
                                  HttpStatus.OK);
    }

    return new ResponseEntity(INVALID, HttpStatus.FORBIDDEN);
  }

  @GetMapping("transactions")
  public ResponseEntity<?> getTransactionHistory(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      List<Transaction> transactions = userServices.getUserTransactionHistory(user);

      return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    return new ResponseEntity(INVALID, HttpStatus.FORBIDDEN);
  }
}