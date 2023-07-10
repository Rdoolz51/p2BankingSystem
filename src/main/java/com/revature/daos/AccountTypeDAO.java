package com.revature.daos;

import com.revature.models.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeDAO extends JpaRepository<AccountType, Integer> {
  AccountType findByType(String type);
  boolean existsByType(String type);
}
