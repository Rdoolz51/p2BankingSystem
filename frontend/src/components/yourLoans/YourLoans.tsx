'use client'
import React, { useState } from "react";
import styles from './YourLoans.module.css';


interface LoanProps {
loans: {
  loanAmount: string;
  type: {
    type: string;
  };
  loanBalance: string;
  interestRate: string;
  user: {
    firstName: string;
    lastName: string;
  }
}[]
  
}

const fetchRoutes = async (active, token) => {
  try{
    const res = await fetch(`${process.env.API_URL}/mybank/${active}`, {
      headers: {
        Authorization: 'Bearer ' + token,
        'Content-Type': 'application/json'
      }
    }) 
      const response = await res.json();
      return response;
    } catch(e) {
      console.log("ERROR" , e)
    }
}

const YourLoans: React.FC<LoanProps> = (props) => {

  const [loanData, setLoanData] = useState(Object.values(props));

  return (
    <div>
      <h2 className={styles.cardType}>Loans</h2>
    <div className={styles.accountContainer}>
      {loanData.map((loan, index) => (
        <div key={index} className={styles.divDiv}>
      <div className={styles.leftContainer}>
        <h2 className={styles.accountType}>{loan.user.firstName}'s {loan.type.type} Loan</h2>  
        <h2 className={styles.balance}>Initial Loan Amount: <span>${(parseInt(loan.loanAmount).toFixed(2))}</span></h2>
        <h2 className={styles.balance}>Current Balance: <span>${(parseInt(loan.loanBalance).toFixed(2))}</span></h2>
        <h2 className={styles.balance}>Interest Rate: <span>${(loan.interestRate)}%</span></h2>
      </div>
      <div className={styles.buttonContainer}>
          <div className={styles.button}>Make Payment</div>
        </div>
      <div className={styles.transactionContainer}>
        {/* <h2>Recent Transactions</h2> */}
      </div>
      </div>
    ))}
    </div>
    </div>
  );
}

export default YourLoans;