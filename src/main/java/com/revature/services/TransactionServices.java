package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionDAO;
import com.revature.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;

@Service
@Transactional(rollbackOn = SQLException.class)
public class TransactionServices {

  private final TransactionDAO transactionDAO;

  private final AccountDAO accountDAO;

  @Autowired
  public TransactionServices(TransactionDAO transactionDAO,
                             AccountDAO accountDAO) {
    this.transactionDAO = transactionDAO;
    this.accountDAO = accountDAO;
  }
}
