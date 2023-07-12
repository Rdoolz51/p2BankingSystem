package com.revature.dtos;

import com.revature.models.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountDTO {
  private AccountType type;
  private String pin;
  private String fakeAccountId;
}
