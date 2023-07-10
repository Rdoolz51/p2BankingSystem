package com.revature.daos;

import com.revature.models.CreditCard;
import com.revature.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardDAO extends JpaRepository<CreditCard, Integer> {
  List<CreditCard> findByStatus_Status(String status);
  List<CreditCard> findByUser_Id(int cid);
}
