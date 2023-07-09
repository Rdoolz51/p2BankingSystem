package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private int id;

  @Column(nullable = false)
  private String street;

  @Column(nullable = false)
  private String city;

  @ManyToOne
  private State state;

  @ManyToOne
  private ZipCode zip;

  @ManyToMany(mappedBy = "userAddresses")
  @JsonIgnore
  private Set<User> usersResiding;
}
