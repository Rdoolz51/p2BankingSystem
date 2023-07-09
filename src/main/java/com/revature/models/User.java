package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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
  
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
      name = "users_addresses",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    )
    private Set<Address> userAddresses;

    @Column(name = "income")
    private double yearlyIncome;
  
     public User(String firstName, String lastName, String email,
                String password,
                String phoneNumber, Role role, Set<Address> userAddresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.userAddresses = userAddresses;
    }
}
