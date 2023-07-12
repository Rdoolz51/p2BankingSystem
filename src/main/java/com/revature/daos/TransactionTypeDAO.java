package com.revature.daos;

import com.revature.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeDAO extends JpaRepository<TransactionType, Integer> {
  TransactionType findByType(String type);
}
