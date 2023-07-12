package com.revature.dtos;

import com.revature.models.CreditCard;
import com.revature.models.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransactionDTO {
  private CreditCard creditCard;
  private String amount;
  // Should be Credit or Debit for CC transactions
  private TransactionType type;
  // The account the money is going to for a debit or coming from for a credit
  private int transactionAcctId;
}
