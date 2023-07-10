package com.revature.dtos;

import com.revature.models.LoanType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoanDTO {
  private String amount;
  private LoanType type;
  private String interestRate;
}
