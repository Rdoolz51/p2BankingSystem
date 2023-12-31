package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
