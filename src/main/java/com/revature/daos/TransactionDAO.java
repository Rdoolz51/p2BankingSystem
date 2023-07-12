package com.revature.daos;

import com.revature.models.Transaction;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer> {
  List<Transaction> findByUserAccount_User(User user);
}
