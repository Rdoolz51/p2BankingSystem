'use client'
import styles from './YourAccounts.module.css';
import { useSession } from 'next-auth/react';
import React, { useState, useReducer } from "react";
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

const submitWithdrawal = async (data:any, token:String) => {
  try{
    const res = await fetch(`${process.env.API_URL}/mybank/withdrawal`, {
      method: 'PUT',
      headers: {
        Authorization: 'Bearer ' + token,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    }) 
      const response = await res.json();
      return response;
    } catch(e) {
      console.log("ERROR" , e)
    }
}

const submitDeposit = async (data:any, token:String) => {
  try{
    const res = await fetch(`${process.env.API_URL}/mybank/deposit`, {
      method: 'PUT',
      headers: {
        Authorization: 'Bearer ' + token,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    }) 
      const response = await res.json();
      return response;
    } catch(e) {
      console.log("ERROR" , e)
    }
}


const YourAccounts: React.FC<AccountProps> = (props) => {
  const session = useSession();
  const userToken = session?.data?.user?.token;

  const [ignored, forceUpdate] = useReducer(x => x + 1, 0);
  const [accountData, setAccountData] = useState(Object.values(props));
  const [withdrawAmount, setWithdrawAmount] = useState(0);
  const [isWithdrawOpen, setIsWithdrawOpen] = useState(false);

  const [depositAmount, setDepositAmount] = useState(0);
  const [isDepositOpen, setIsDepositOpen] = useState(false);

  const [isSameId, setIsSameId] = useState()

  const depositMoney = async (e:any) => {
    if(depositAmount > 0) {
      const data = {
        accountId: e.target.value,
        amount: depositAmount
      }

      const res = await submitDeposit(data, userToken)
      if(res) {
        forceUpdate();
        setDepositAmount(0);
        setIsDepositOpen(false);
      } else {
        console.error('uh oh')
      }
    } else {
      console.log('enter more than 0')
    }
  };

  const withdrawMoney = async (e:any) => {
    if(withdrawAmount > 0) {
      const data = {
        accountId: e.target.value,
        amount: withdrawAmount
      }

      const res = await submitWithdrawal(data, userToken)
      if(res) {
        forceUpdate();
        setWithdrawAmount(0);
        setIsWithdrawOpen(false);
      } else {
        console.error('uh oh')
      }
    } else {
      console.log('enter more than 0')
    }
  };

  const handleWithdrawClick = (e:any) => {
    setIsSameId(e.target.value)
    setIsWithdrawOpen(!isWithdrawOpen);
  };

  const handleDepositClick = (e:any) => {
    setIsSameId(e.target.value)
    setIsDepositOpen(!isDepositOpen);
  };

  

  return (
    <div className={styles.accountContainer}>
      {accountData.map((account, index) => (
        <div key={index} className={styles.divDiv}>
          <div className={styles.leftContainer}>
            <h2 className={styles.accountType}>{account.user.firstName}'s {account.type.type} {account.fakeAccountId} </h2>  
            <h2 className={styles.balance}>Current Balance: <span>${parseInt(account.balance).toFixed(2)}</span></h2>
            
            {isWithdrawOpen && isSameId==account.accountID ? (
              <div>
                <button value={account.accountID} onClick={handleWithdrawClick}>Withdraw</button>
                <input
                  type="number"
                  value={withdrawAmount}
                  onChange={(e) => setWithdrawAmount(parseInt(e.target.value))}
                  placeholder="Enter amount"
                />
                <button value={account.accountID} onClick={withdrawMoney}>Submit</button>
              </div>
            ) : (
              <button value={account.accountID} onClick={handleWithdrawClick}>Withdraw</button>
            )}

            {isDepositOpen && isSameId==account.accountID ? (
              <div>
                <button value={account.accountID} onClick={handleDepositClick}>Deposit</button>
                <input
                  type="number"
                  value={depositAmount}
                  onChange={(e) => setDepositAmount(parseInt(e.target.value))}
                  placeholder="Enter amount"
                />
                <button value={account.accountID} onClick={depositMoney}>Submit</button>
              </div>
            ) : (
              <button value={account.accountID} onClick={handleDepositClick}>Deposit</button>
            )}
              
          </div>
          <div className={styles.transactionContainer}>
            {/* <h2>Recent Transactions</h2> */}
          </div>
          <NewTransfer accountID={account.accountID} />
        </div>
      ))}
    </div>
  );
};

export default YourAccounts;
