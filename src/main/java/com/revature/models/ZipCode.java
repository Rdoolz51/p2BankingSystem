package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zip_codes")
public class ZipCode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "zip_id")
  private int id;

  @Column(nullable = false)
  private String zipCode;
}
