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
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private int creditID;

    @Column(name = "credit_limit", nullable = false)
    private String creditLimit;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_expiration")
    private String cardExpiration;

    @Column(name = "balance", nullable = false)
    private String balance;

    @Column(name = "interest_rate", nullable = false)
    private String interestRate;

    @Column(name = "annual_fee", nullable = false)
    private String annualFee;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    private User user;

    @NotNull
    @ManyToOne(targetEntity = Status.class)
    private Status status;
}