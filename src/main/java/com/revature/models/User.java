package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "phone_number", nullable = false, unique = true)
  private String phoneNumber;

  @ManyToOne
  private Role role;

  @ManyToOne
  private Address address;

  @Column(name = "income")
  private BigDecimal yearlyIncome;

  public User(String firstName, String lastName, String email, String password,
              String phoneNumber, Role role, Address address,
              BigDecimal yearlyIncome) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.role = role;
    this.address = address;
    this.yearlyIncome = yearlyIncome;
  }
}
