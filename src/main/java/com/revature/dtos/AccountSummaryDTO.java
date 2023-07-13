package com.revature.dtos;

import com.revature.models.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountSummaryDTO {
  private List<String> accountIds;
  private List<String> fakeIds;
  private List<AccountType> type;
  private String firstName;
  private String lastName;
}
