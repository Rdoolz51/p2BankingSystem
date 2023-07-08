package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
  List<Account> findByEmail(String email);
  List<Account> findByType(AccountType type);
}
