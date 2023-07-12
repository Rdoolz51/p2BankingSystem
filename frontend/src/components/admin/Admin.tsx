'use client'
import Link from "next/link";
import { useSession } from "next-auth/react"
import { redirect } from 'next/navigation'
import { useState } from 'react';

import styles from './Admin.module.css'
import Loans from "./loans/Loans";
import CreditCards from "./creditCards/CreditCards";

const names = {
  loans: 'loans',
  cards: 'cards'
}

const Admin: React.FC<any> = (props:any) => {
  const { status } = useSession({
    required: true,
    onUnauthenticated() {
        redirect('/auth/login')
    },
  })

  const data = props?.data;
  
  const [activeButton, setActiveButton] = useState('')

  const handlerSelector = (e:any) => {
    setActiveButton(e.target.name)
  }

  return (
    <main>

      {data ? (
        <div className={styles.buttonContainer}>
          <button name={names.cards}
            onClick={handlerSelector} 
            className={activeButton === names.cards ? styles.activeButton : ''} 
            disabled={activeButton === names.cards}  
          >
            Card Applications
          </button>

          <button name={names.loans}
            onClick={handlerSelector} 
            className={activeButton === names.loans ? styles.activeButton : ''} 
            disabled={activeButton === names.loans}  
          >
            Loan Applications
          </button>

          <div>
            {activeButton === names.cards &&
              <CreditCards {...props.data.CCData} />
            }
          </div>

          <div>
            {activeButton === names.loans && 
              <Loans {...props.data.loanData} />
            }
          </div>
        </div>
      ) : (
        <div>Loading....</div>
      )}

    </main>
  )
}

export default Admin;