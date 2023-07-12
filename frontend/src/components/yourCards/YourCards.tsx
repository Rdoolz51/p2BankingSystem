'use client'
import { useState } from 'react';
import styles from './YourCards.module.css';

interface CardProps {
  interestRate: string;
  cardNumber: string;
  balance: string;
  creditLimit: string;
  cardExpiration: string;
  annualFee: string;
  user: {
    firstName: string;
    lastName: string;
  }
}

const YourCards: React.FC<CardProps> = (props) => {
  const initialCardBalance = parseInt(props.balance);
  const [balance, setBalance] = useState(initialCardBalance);


  const spendMoney = (amount: number) => {
    if(balance + amount <= parseInt(props.creditLimit)) {
      setBalance(prevBalance => prevBalance + amount);
    } else {
      alert("Credit limit exceeded");
    }
  }

  return (
    <>
    <h2 className={styles.cardType}>Credit Card</h2>
    <div className={styles.card}>
        <h2 className={styles.bankName}>Pursue</h2>
        <h2 className={styles.cardNumber}>**** **** **** {props.cardNumber.substring(12)}</h2>
        <h2 className={styles.expirationDate}>Exp: {props.cardExpiration}</h2>
    </div>
        <div className={styles.cardLimit}>
        <h2 className={styles.creditLimit}>Credit Limit: ${parseInt(props.creditLimit).toFixed(2)}</h2>
        </div>
        <div className={styles.cardBalance}>
        <h2 className={styles.balance}>Balance: ${balance.toFixed(2)}</h2>
      </div>
      <button className={styles.spendButton} onClick={() => spendMoney(50)}>Spend $50</button>
    </>
  )
}

export default YourCards;
