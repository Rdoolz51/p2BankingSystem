package com.revature.controllers;

import com.revature.daos.CreditCardDAO;
import com.revature.daos.LoanDAO;
import com.revature.dtos.UpdateDTO;
import com.revature.models.CreditCard;
import com.revature.models.Loan;
import com.revature.models.User;
import com.revature.services.AdminServices;
import com.revature.services.UserServices;
import com.revature.util.GenerateCC;
import com.revature.util.GenerateExp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AdminController {
  private static final String BIN = "5555";
  private static final int CC_DIGITS = 16;
  private final String INVALID = "User token is either invalid or expired";
  private final String GENERIC = "Something went wrong";
  private final UserServices userServices;
  private final AdminServices adminServices;
  private final LoanDAO loanDAO;
  private final CreditCardDAO creditCardDAO;

  @Autowired
  public AdminController(UserServices userServices, AdminServices adminServices,
                         LoanDAO loanDAO, CreditCardDAO creditCardDAO) {
    this.userServices = userServices;
    this.adminServices = adminServices;
    this.loanDAO = loanDAO;
    this.creditCardDAO = creditCardDAO;
  }

  @GetMapping("loan-apps")
  public ResponseEntity<?> getAllLoanAppsHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(adminServices.getAllLoans(), HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @GetMapping("loan-pending")
  public ResponseEntity<?> getAllPendingLoanAppsHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(adminServices.getAllLoansByStatus("Pending"),
                                  HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @GetMapping("loans-denied")
  public ResponseEntity<?> getAllLoansDeniedHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(adminServices.getAllLoansByStatus("Denied"),
                                  HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @GetMapping("loans-approved")
  public ResponseEntity<?> getAllLoansApprovedHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(adminServices.getAllLoansByStatus("Approved"),
                                  HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  /**
   * @param token
   * @param cid
   * @return
   */
  @GetMapping("/{cid}/loans")
  public ResponseEntity<?> getUserLoansHandler(
    @RequestHeader("Authorization") String token,
    @PathVariable int cid) {
    User user = userServices.checkUserToken(token);

    if (user != null && cid > 0) {
      List<Loan> loans = adminServices.getAllUserLoansById(cid);

      if (loans != null) {
        return new ResponseEntity<>(loans, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(GENERIC, HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  /**
   * @param token
   * @param cid
   * @return
   */
  @GetMapping("/{cid}/cards")
  public ResponseEntity<?> getUserCreditCardsHandler(
    @RequestHeader("Authorization") String token,
    @PathVariable int cid) {
    User user = userServices.checkUserToken(token);

    if (user != null && cid > 0) {
      List<CreditCard> cards = adminServices.getAllUserCreditCardsById(cid);

      if (cards != null) {
        return new ResponseEntity<>(cards, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(GENERIC, HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  /**
   * @param token
   * @return
   */
  @GetMapping("cc-apps")
  public ResponseEntity<?> getAllCreditCardAppsHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(adminServices.getAllCreditCards(),
                                  HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  /**
   * @param token
   * @return
   */
  @GetMapping("cc-pending")
  public ResponseEntity<?> getAllPendingCreditCardAppsHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(
        adminServices.getAllCreditCardsByStatus("Pending"), HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  /**
   * @param token
   * @return
   */
  @GetMapping("cc-denied")
  public ResponseEntity<?> getAllCreditCardAppsDeniedHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(
        adminServices.getAllCreditCardsByStatus("Denied"), HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  /**
   * @param token
   * @return
   */
  @GetMapping("cc-approved")
  public ResponseEntity<?> getAllCreditCardAppsApprovedHandler(
    @RequestHeader("Authorization") String token) {
    User user = userServices.checkUserToken(token);

    if (user != null) {
      return new ResponseEntity<>(
        adminServices.getAllCreditCardsByStatus("Approved"), HttpStatus.OK);
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  /**
   * @param token
   * @param update
   * @return
   */
  @PutMapping("/loans")
  public ResponseEntity<?> updateLoanStatusHandler(
    @RequestHeader("Authorization") String token,
    @RequestBody UpdateDTO update) {
    User user = userServices.checkUserToken(token);

    if (update == null) {
      return new ResponseEntity<>("Loan update information was null",
                                  HttpStatus.BAD_REQUEST);
    }

    if (user != null) {
      User customer = userServices.getUserById(update.getUserId());

      if (customer == null) {
        return new ResponseEntity<>("Customer information was null",
                                    HttpStatus.BAD_REQUEST);
      }

      if (update.getStatus().getStatus() != null && update.getAppId() > 0) {
        Loan updated = loanDAO.findById(update.getAppId()).get();

        if (updated != null && updated.getUser().getId() == customer.getId()) {
          updated.setStatus(update.getStatus());

          if (update.getStatus().getStatus().equals("Approved")) {
            String amount = updated.getLoanAmount();

            updated.setLoanBalance(amount);
          }

          return new ResponseEntity<>(adminServices.updateLoanStatus(updated),
                                      HttpStatus.OK);
        }
      } else {
        return new ResponseEntity<>(GENERIC, HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }

  @PutMapping("/cards")
  public ResponseEntity<?> updateCreditCardStatusHandler(
    @RequestHeader("Authorization") String token,
    @RequestBody UpdateDTO update) {
    User user = userServices.checkUserToken(token);

    if (update == null) {
      return new ResponseEntity<>("Credit card update information was null",
                                  HttpStatus.BAD_REQUEST);
    }

    if (user != null) {
      User customer = userServices.getUserById(update.getUserId());

      if (customer == null) {
        return new ResponseEntity<>("Customer information was null or does not exist",
                                    HttpStatus.BAD_REQUEST);
      }

      if (update.getStatus().getStatus() != null && update.getAppId() > 0) {
        CreditCard updated = creditCardDAO.findById(update.getAppId()).get();

        if (updated != null && updated.getUser().getId() == customer.getId()) {
          updated.setStatus(update.getStatus());

          if (update.getStatus().getStatus().equals("Approved")) {
            String ccn = GenerateCC.generate(BIN, CC_DIGITS);
            System.out.println(ccn);
            updated.setCardNumber(ccn);
            updated.setCardExpiration(GenerateExp.getExpirationDate());
          }

          return new ResponseEntity<>(
            adminServices.updateCreditCardStatus(updated),
            HttpStatus.OK);
        }
      } else {
        return new ResponseEntity<>(GENERIC, HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity<>(INVALID, HttpStatus.FORBIDDEN);
  }
}
