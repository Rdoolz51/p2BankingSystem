package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.CreditCardDAO;
import com.revature.daos.LoanDAO;
import com.revature.daos.UserDAO;
import com.revature.models.CreditCard;
import com.revature.models.Loan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminServices {
  private final AccountDAO accountDAO;
  private final LoanDAO loanDAO;
  private final CreditCardDAO creditCardDAO;
  private final UserDAO userDAO;

  @Autowired
  public AdminServices(AccountDAO accountDAO, LoanDAO loanDAO, CreditCardDAO creditCardDAO,
                       UserDAO userDAO) {
    this.accountDAO = accountDAO;
    this.loanDAO = loanDAO;
    this.creditCardDAO = creditCardDAO;
    this.userDAO = userDAO;
  }

  /**
   *
   * @return
   */
  public List<Loan> getAllLoans() {
    log.info("Retrieved all loan apps");
    return loanDAO.findAll();
  }

  /**
   *
   * @param status
   * @return
   */
  public List<Loan> getAllLoansByStatus(String status) {
    log.info("Retrieved all " + status + " loan apps");
    return loanDAO.findByStatus_Status(status);
  }

  /**
   *
   * @return
   */
  public List<CreditCard> getAllCreditCards() {
    log.info("Retrieved all credit card apps");
    return creditCardDAO.findAll();
  }

  /**
   *
   * @param status
   * @return
   */
  public List<CreditCard> getAllCreditCardsByStatus(String status) {
    log.info("Retrieved all " + status + " credit card apps");
    return creditCardDAO.findByStatus_Status(status);
  }

  /**
   *
   * @param loan
   * @return
   */
  public Loan updateLoanStatus(Loan loan) {
    if (loan != null && loan.getStatus() != null) {
      log.info("Loan status has been updated to " + loan.getStatus().getStatus());
      Loan complete = loanDAO.save(loan);

      return complete;
    }

    log.warn("Loan status could not be updated");
    return null;
  }

  /**
   *
   * @param creditCard
   * @return
   */
  public CreditCard updateCreditCardStatus(CreditCard creditCard) {
    if (creditCard != null && creditCard.getStatus() != null) {
      log.info("Credit card status has been updated to " + creditCard.getStatus().getStatus());
      CreditCard complete = creditCardDAO.save(creditCard);

      return complete;
    }

    log.warn("Credit card status could not be updated");
    return null;
  }

  /**
   *
   * @param cid
   * @return
   */
  public List<Loan> getAllUserLoansById(int cid) {
    if (userDAO.existsById(cid)) {
      log.info("Retrieved all loans for user ID: " + cid);
      return loanDAO.findByUser_Id(cid);
    }

    log.warn("Could not retrieve loans for user ID: " + cid);
    return null;
  }

  /**
   *
   * @param cid
   * @return
   */
  public List<CreditCard> getAllUserCreditCardsById(int cid) {
    if (userDAO.existsById(cid)) {
      log.info("Retrieved all credit cards for user ID: " + cid);
      return creditCardDAO.findByUser_Id(cid);
    }

    log.warn("Could not retrieve credit cards for user ID: " + cid);
    return null;
  }
}
