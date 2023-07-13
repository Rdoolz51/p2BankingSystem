package com.revature.dtos;

import com.revature.models.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountSummaryDTO {
  private Map<Integer, List<String>> summary;
  private String firstName;
  private String lastName;
}
