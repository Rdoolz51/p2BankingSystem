package com.revature.daos;

import com.revature.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDAO extends JpaRepository<Loan, Integer> {
}
