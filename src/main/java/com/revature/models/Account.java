package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private String balance;

    @ManyToOne
    private AccountType type;

    private String pin;

    @ManyToOne(targetEntity = User.class)
    private User user;

    public Account(String balance, AccountType type, String pin, User user) {
        this.balance = balance;
        this.type = type;
        this.pin = pin;
        this.user = user;
    }
}
