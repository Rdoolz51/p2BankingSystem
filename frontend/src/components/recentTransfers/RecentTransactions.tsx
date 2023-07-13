'use client'
import { useSession } from "next-auth/react"
import { redirect } from 'next/navigation'
import { useState, useEffect } from 'react';

import styles from './RecentTransactions.module.css';


const RecentTransactions: React.FC<any> = (props: any) => {
  const session = useSession();
  const userToken = session?.data?.user?.token;

  const [state, setState] = useState(Object.values(props));


  console.log('proooooooops >>> ', state);

  if (!state || state.length < 1) {
    return (<div></div>)
  }

  return (
    <div className={styles.accountContainer}>
      <h1 className={styles.title}>Recent Transactions</h1>
      {state &&
        state.map((t: any) => {
          const dateString = t.transactionDate;
          const date = new Date(dateString);

          const day = date.getDate();
          const month = date.getMonth() + 1; // Month is zero-based, so we add 1
          const year = date.getFullYear();
          const formattedDate = `${day}/${month}/${year}`;
          return (
            <>
            <div className={styles.leftContainer}>
              <table className={styles.table}>
                <thead className={styles.thead}>
                  <tr>
                    <th>ID</th>
                    <th>Amount</th>
                    <th>Date</th>
                  </tr>
                </thead>
                <tbody className={styles.tbody}>
                  <tr key={t.transactionID}>
                    <td>{t.userAccount.accountID}</td>
                    <td>${t.amount}</td>
                    <td>{formattedDate}</td>
                  </tr>
                </tbody>
              </table>
            </div>
            </>
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