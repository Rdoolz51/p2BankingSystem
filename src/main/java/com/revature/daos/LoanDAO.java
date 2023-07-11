package com.revature.daos;

import com.revature.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanDAO extends JpaRepository<Loan, Integer> {
  List<Loan> findByStatus_Status(String status);
  List<Loan> findByUser_Id(int cid);
}
