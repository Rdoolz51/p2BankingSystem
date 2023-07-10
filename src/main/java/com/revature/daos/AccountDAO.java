package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
  List<Account> findByUser(User user);
  List<Account> findByType(AccountType type);
}
