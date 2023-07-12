package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountTransactionDTO {
  private int accountId;
  private String amount;
}
