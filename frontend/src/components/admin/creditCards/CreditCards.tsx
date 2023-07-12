'use client'
import { useSession } from "next-auth/react"
import { redirect } from 'next/navigation'
import { useState } from 'react';

import { approveOrDenyCC, getPendingCreditCards } from "@/app/api/admin";

import styles from './CreditCards.module.css'

const CreditCards: React.FC<any> = (props:any) => {
  console.log('PPPPPPPPPPP', props);
  const [CCData, setCCData] = useState(Object.values(props))
  console.log(CCData);

  const session = useSession()
  const token = session?.data?.user?.token
  console.log("WWWWWWWWWWWWWWWWWWWWWW", token);

  const handleDeny = async (e:any) => {
    const data = { // yes, I'm hardcoding the values. I'm tired
      status: {
        id: 3,
        status: "Denied"
      },
      userId: e.target.name,
      appId: e.target.value
    }

    const res = await approveOrDenyCC(token, data)
    const newData = await getPendingCreditCards(token);
    setCCData(newData)
  }

  const handleApprove = async (e:any) => {
    const data = { // yes, I'm hardcoding the values. I'm tired
      status: {
        id: 3,
        status: "Approve"
      },
      userId: e.target.name,
      appId: e.target.value
    }

    const res = await approveOrDenyCC(token, data)
    const newData = await getPendingCreditCards(token);
    setCCData(newData)
  }

  return (
    <div className={styles.accountContainer}>
      {!CCData ? (
        <div>loading</div>
      ) : (
        CCData.map((c:any) => {
          return (
            <div className={styles.leftContainer} key={c.creditID}>
              <h2 className={styles.accountType}>Applicant: {c.user.firstName} {c.user.lastName}</h2>  
              <h2 className={styles.accountNumber}>Card Limit: {c.creditLimit}</h2>
              <h2 className={styles.accountNumber}>Annual Fee / Interest Rate: {c.annualFee} / {c.interestRate}</h2>
              <div className={styles.buttonContainer}>
                <button className={styles.button} name={c.user.id} value={c.creditID} style={{ backgroundColor: '#8b2020' }} onClick={handleDeny}>Deny Card</button>
                <button className={styles.button} name={c.user.id} value={c.creditID} style={{ backgroundColor: '#16168f' }} onClick={handleApprove}>Approve Card</button>
              </div>
            </div>
          )
        })

      )}

    </div>
  )
}

export default CreditCards;