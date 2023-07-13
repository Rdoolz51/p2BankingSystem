'use client'
import styles from './YourAccounts.module.css';
import { useSession } from 'next-auth/react';
import React, { useState } from "react";
import Modal from "react-modal";
import NewTransfer from '../transfers/NewTransfer';

interface AccountProps {
  accounts: {
    type: {
      type: string;
    };
    user: {
      firstName: string;
    };
    balance: any;
    fakeAccountId: any;
  }[];
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

  const [accountData, setAccountData] = useState(Object.values(props));

  // console.log("6666666666666", accountData)
  const depositMoney = (amount: number) => {};
  const withdrawMoney = (amount: number) => {};
  const transferMoney = (amount: number) => {};
  

  return (
    <div>

            <h2 className={styles.cardType}>Accounts</h2>
    <div className={styles.accountContainer}>
      {accountData.map((account, index) => (
        <div key={index} className={styles.divDiv}>
          <div className={styles.leftContainer}>
            <h2 className={styles.accountType}>{account.user.firstName}'s {account.type.type} {account.fakeAccountId} </h2>  
            <h2 className={styles.balance}>Current Balance: <span>${parseInt(account.balance).toFixed(2)}</span></h2>

          </div>
          <div className={styles.transactionContainer}>
            {/* <h2>Recent Transactions</h2> */}
          </div>
          <NewTransfer accountID={account.accountID} />
        </div>
      ))}
    </div>
          </div>
  );
};

export default YourAccounts;
