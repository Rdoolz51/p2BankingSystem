package com.revature.dtos;

import com.revature.models.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class AccountTransactionDTO {
  private int accountId;
  private String amount;
}
