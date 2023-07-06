package com.revature.daos;

import com.revature.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer>
{
    Transaction getTransactionByID(int transactionID);
    List<Transaction> getTransactionsByAccountID(int accountID);
    void addTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction);
    void deleteTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
}
