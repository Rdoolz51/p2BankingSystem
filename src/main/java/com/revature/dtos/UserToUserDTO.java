package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserToUserDTO {
  private String fromEmail;
  private String toEmail;
  private int fromAcct;
  private int toAcct;
  private String amount;
}
