package com.revature.dtos;

import com.revature.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateDTO {
  private Status status;
  private int userId;
  private int appId;
}
