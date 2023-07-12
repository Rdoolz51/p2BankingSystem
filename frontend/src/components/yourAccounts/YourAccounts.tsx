import styles from './YourAccounts.module.css';
import { useSession } from 'next-auth/react';
import React, { useState } from "react";
import Modal from "react-modal";
import NewTransfer from '../transfers/NewTransfer';

interface AccountProps {

  type: {
    type:string;
  };


  user: {
    firstName: string;
  };
 
  balance: any;
  accountID: Number;
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


const YourAccounts: React.FC<AccountProps> = (props) => {
  if(!props.user || !props.type || !props.balance) {
    return <div></div>
  }
  // console.log(props);
  
let accountNumber = '12345312454325'
  const depositMoney = (amount: number) => {
    
  }

  const withdrawMoney = (amount: number) => {
  
  }
  
  const transferMoney = async (amount: number) => {
   
  }

  return (
    <div className={styles.accountContainer}>
      <div className={styles.leftContainer}>
        <h2 className={styles.accountType}>{props.user.firstName}'s {props.type.type} : {accountNumber}</h2>  
        <h2 className={styles.balance}>Current Balance: <span>${(parseInt(props.balance).toFixed(2))}</span></h2>
        <div className={styles.buttonContainer}>
          <div className={styles.button} onClick={() => depositMoney(10)}>Deposit $10</div>
          <div className={styles.button} onClick={() => withdrawMoney(10)}>Withdraw $10</div>
          <div className={styles.button} onClick={() => transferMoney(10)}>Transfer $10</div>
        </div>
      </div>
      <div className={styles.transactionContainer}>
        {/* <h2>Recent Transactions</h2> */}
      </div>
      <NewTransfer accountID={props.accountID} />
    </div>
  );
}

export default YourAccounts;