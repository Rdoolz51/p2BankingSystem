'use client'
import { useState } from 'react';
import styles from './YourCards.module.css';

interface CardProps {
  cardType: string;
  cardNumber: string;
  initialCardBalance: number;
  creditLimit: number;
  expirationDate: string;
}

const YourCards: React.FC<CardProps> = ({ cardType, cardNumber, initialCardBalance, creditLimit, expirationDate }) => {
  const [balance, setBalance] = useState(initialCardBalance);

  const spendMoney = (amount: number) => {
    if(balance + amount <= creditLimit) {
      setBalance(prevBalance => prevBalance + amount);
    } else {
      alert("Credit limit exceeded");
    }
  }

  return (
    <>
    <h2 className={styles.cardType}>{cardType}</h2>
    <div className={styles.card}>
        <h2 className={styles.bankName}>Nameless Bank</h2>
        <h2 className={styles.cardNumber}>**** **** **** {cardNumber}</h2>
        <h2 className={styles.expirationDate}>Exp: {expirationDate}</h2>
    </div>
        <div className={styles.cardLimit}>
        <h2 className={styles.creditLimit}>Credit Limit: ${creditLimit.toFixed(2)}</h2>
        </div>
        <div className={styles.cardBalance}>
        <h2 className={styles.balance}>Balance: ${balance.toFixed(2)}</h2>
      </div>
      <button className={styles.spendButton} onClick={() => spendMoney(50)}>Spend $50</button>
    </>
  )
}

export default YourCards;
