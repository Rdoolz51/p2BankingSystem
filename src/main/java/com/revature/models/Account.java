package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountID;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "account_type", nullable = false)  //Savings, Checking, Travel, etc...
    private String accountType;

    private String pin;

    @ManyToOne(targetEntity = User.class)
    private User user;


    public Account(User user, Account accountType) {
    }
}
