'use client'
import { useState } from 'react';
import styles from './YourCards.module.css';
import { useSession } from 'next-auth/react';

interface CardProps {
  cards: {
    
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

// function formatDate(input: string): string {
//   const date = new Date(input);
//   const month = date.getMonth() + 1; 
//   const year = date.getFullYear();

//   return `${month.toString().padStart(2, '0')}/${year.toString().slice(-2)}`;
// }

const YourCards: React.FC<CardProps> = (props) => {

  const [cardData, setCardData] = useState(Object.values(props));

  // console.log("33333333333333333", cardData)
  const session = useSession();
  const userData = session.data?.user;
  
  
  // const inputDate = cardData.cardExpiration;
  // const outputDate = formatDate(inputDate);


  // const makePayment = (amount: number) => {
  //   if(balance + amount <= parseInt(cardData.creditLimit)) {
  //     setBalance(prevBalance => prevBalance + amount);
  //   } else {
  //     alert("Credit limit exceeded");
  //   }
  // }

  return (
    <div className={styles.pageTitle}>
      <h2 className={styles.cardType}>Credit Cards</h2>
    <div className={styles.cardBox}>
      {cardData.map((card, index) => (
        <div key={index} className={styles.divDiv}>

    <div className={styles.card}>
        <h2 className={styles.bankName}>Pursue</h2>
        <h2 className={styles.cardNumber}>**** **** **** {card.cardNumber.substring(12)}</h2>
        <h2 className={styles.expirationDate}>Exp: {card.cardExpiration}</h2>
    </div>
    <div className={styles.infoWrapper}>

        <div className={styles.cardLimit}>
        <h2 className={styles.creditLimit}>Credit Limit: ${parseInt(card.creditLimit).toFixed(2)}</h2>
        </div>
        <div className={styles.cardBalance}>
        <h2 className={styles.balance}>Balance: ${parseInt(card.balance).toFixed(2)}</h2>
    </div>
      </div>
      <div className={styles.buttonContainer}>
          <div className={styles.button}>Make Payment</div>
      </div>
    </div>
    ))}
    </div>
    </div>
    )
  }
  
export default YourCards;
