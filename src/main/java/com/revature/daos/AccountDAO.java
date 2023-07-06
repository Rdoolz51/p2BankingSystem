package com.revature.daos;

import com.revature.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>
{
    Account getAccountByID(int accountID);
    List<Account> getAccountsByUserID(int userID);
    void addAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Account account);
    List<Account> getAllAccounts();
}
