package com.revature.daos;

import com.revature.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer>
{
    Transaction getTransactionByTransactionId(int transactionId);
    List<Transaction> getTransactionByAccount(String account);
}
