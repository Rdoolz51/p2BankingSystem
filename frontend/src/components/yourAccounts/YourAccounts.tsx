'use client'
import { useState } from 'react';
import styles from './YourAccounts.module.css';



interface AccountProps {
  accountType: string;
  accountNumber: string;
  initialBalance: number;
}

const YourAccounts: React.FC<AccountProps> = ({ accountType, accountNumber, initialBalance }) => {
  const [balance, setBalance] = useState(initialBalance);
  const [availableBalance, setAvailableBalance] = useState(initialBalance);

  const depositMoney = (amount: number) => {
    setBalance(prevBalance => prevBalance + amount);
    setTimeout(() => setAvailableBalance(prevBalance => prevBalance + amount), 2500)
  }

  const withdrawMoney = (amount: number) => {
    if(balance - amount >= 0) {
      setBalance(prevBalance => prevBalance - amount);
      setTimeout(() => setAvailableBalance(prevBalance => prevBalance - amount), 2500)
    } else {
      alert("Insufficient funds");
    }
  }
  
  const transferMoney = (amount: number) => {
    if(balance - amount >= 0) {
        setBalance(prevBalance => prevBalance - amount);
        setTimeout(() => setAvailableBalance(prevBalance => prevBalance - amount), 2500)
        alert("ADD FUNCTIONALITY...but im still takin that money...")
    } else {
        alert("Insufficient funds");
      }
  }

  return (
    <div className={styles.accountContainer}>
      <div className={styles.leftContainer}>
        <h2 className={styles.accountType}>Ryan's {accountType} : {accountNumber}</h2>  
        {/* <h2 className={styles.accountNumber}>Account Number: {accountNumber}</h2> */}
        <h2 className={styles.balance}>Available Balance: <span>${availableBalance.toFixed(2)}</span></h2>
        <h2 className={styles.balance}>Current Balance: <span>${balance.toFixed(2)}</span></h2>
        <div className={styles.buttonContainer}>
          <div className={styles.button} onClick={() => depositMoney(10)}>Deposit $10</div>
          <div className={styles.button} onClick={() => withdrawMoney(10)}>Withdraw $10</div>
          <div className={styles.button} onClick={() => transferMoney(10)}>Transfer $10</div>
        </div>
      </div>
      <div className={styles.transactionContainer}>
        {/* <h2>Recent Transactions</h2> */}
      </div>
    </div>
  );
}

export default YourAccounts;