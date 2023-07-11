package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountTransferDTO {
  private int fromAccountId;
  private int toAccountId;
  private String amount;
}
