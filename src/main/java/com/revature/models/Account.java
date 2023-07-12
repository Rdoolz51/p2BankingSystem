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
    private String balance;

    @ManyToOne
    private AccountType type;

    private String pin;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "fake_account_id")
    private String fakeAccountId;

    public Account(String balance, AccountType type, String pin, User user, String fakeAccountId) {
        this.balance = balance;
        this.type = type;
        this.pin = pin;
        this.user = user;
        this.fakeAccountId = fakeAccountId;
    }
}
