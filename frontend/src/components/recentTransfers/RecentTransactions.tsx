'use client'
import { useSession } from "next-auth/react"
import { redirect } from 'next/navigation'
import { useState, useEffect } from 'react';

import styles from './RecentTransactions.module.css';


const RecentTransactions: React.FC<any> = (props:any) => {
  const session = useSession();
  const userToken = session?.data?.user?.token;

  const [state, setState] = useState(Object.values(props));


  console.log('proooooooops >>> ', state);

  if(!state || state.length < 1) {
    return (<div></div>)
  }
  
  return (
    <div className={styles.accountContainer}>
      {state && 
      state.map((t:any) => {
        const dateString = t.transactionDate;
        const date = new Date(dateString);
        
        const day = date.getDate();
        const month = date.getMonth() + 1; // Month is zero-based, so we add 1
        const year = date.getFullYear();
        const formattedDate = `${day}/${month}/${year}`;
        return (
          <div className={styles.leftContainer} key={t.transactionID}>
            <h2 className={styles.accountType}>{t.userAccount.accountID}</h2>  
            <h2 className={styles.accountNumber}>${t.amount}</h2>
            <h2 className={styles.accountNumber}>{formattedDate}</h2>
            {/* <h2 className={styles.accountNumber}>Balance: ${t.userAccount.balance}</h2> */}
          </div>
        )
      })
    }
    {!props &&
    <div></div>
    }

    </div>
  )
}

export default RecentTransactions;