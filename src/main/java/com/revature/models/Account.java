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

    @ManyToOne(targetEntity = User.class) //TODO: This means that each account can have 1 user, but each user can have many accounts(checking, savings, etc...).
                                          //TODO: Again, just how I visualized it, we can change if this is the harder way to do it.
    private User user;

    public Account(User user, Account accounttype){
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
