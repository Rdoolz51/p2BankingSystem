package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreditDTO {
  private String creditLimit;
  private String interestRate;
  private String annualFee;
}
