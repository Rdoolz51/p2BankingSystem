package com.revature.models;

import com.sun.istack.NotNull;
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

    @Column(name = "loan_amount", nullable = false)
    private String loanAmount;

    @NotNull
    @ManyToOne
    private LoanType type;

    @Column(name = "loan_balance", nullable = false)
    private String loanBalance;

    @Column(name = "interest_rate", nullable = false)
    private String interestRate;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    private User user;

    @NotNull
    @ManyToOne(targetEntity = Status.class)
    private Status status;
}