package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statuses")
public class Status {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "status_id")
  private int id;

  private String status;
}
