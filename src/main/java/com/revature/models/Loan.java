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
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int loanID;

    @Column(name = "loan_amount")
    private String loanAmount;

    @ManyToOne
    private LoanType type;     //Personal, Business, House, Car, etc...

    @Column(name = "loan_balance")
    private String loanBalance;

    @Column(name = "interest_rate")
    private String interestRate;

    @ManyToOne(targetEntity = User.class)  //User Requesting Loan
    private User user;

    private Status status;
}