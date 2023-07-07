package com.revature.dtos;

import com.revature.models.Address;
import com.revature.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterDTO {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String phoneNumber;
  private Role role;
  private Address address;
}
