package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credit_cards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private int creditID;

    @Column(name = "credit_limit")
    private double creditLimit;

    @Column(name = "balance")
    private double balance;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "annual_fee")
    private double annualFee;    // Zero if none obviously for our higher income folks.

    @ManyToOne(targetEntity = User.class)
    private User user;
    
}