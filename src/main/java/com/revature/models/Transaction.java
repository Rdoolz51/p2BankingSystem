package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionID;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "transaction_type", nullable = false, unique = true)
    private String transactionType;

    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;  //TODO: This is just how I visualized it, we can do whatever though!

    @OneToOne(targetEntity = Account.class)
    private Account senderAccount;
    //TODO: This is just how I visualized it, we can do whatever though!
    @OneToOne(targetEntity = Account.class)
    private Account receiverAccount;





}
