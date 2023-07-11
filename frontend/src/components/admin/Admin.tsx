'use client'
import Link from "next/link";
import { useSession } from "next-auth/react"
import { redirect } from 'next/navigation'
import { useState } from 'react';

import styles from './Admin.module.css'
import Loans from "./loans/Loans";

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

  console.log("Admin Props >>> ", props.data);
  const data = props?.data;
  
  const [activeButton, setActiveButton] = useState('')

  const handlerSelector = (e:any) => {
    setActiveButton(e.target.name)
  }
  console.log(activeButton);

  return (
    <main>

      {data ? (
        <div className={styles.buttonContainer}>
          <button name={names.cards}
            onClick={handlerSelector} 
            className={activeButton === names.cards ? styles.activeButton : ''} 
            disabled={activeButton === names.cards}  
          >
            Your Cards
          </button>

          <button name={names.loans}
            onClick={handlerSelector} 
            className={activeButton === names.loans ? styles.activeButton : ''} 
            disabled={activeButton === names.loans}  
          >
            Your Loans
          </button>

          {/* <div>
            {activeButton === names.cards &&
              <YourCards {...props} />
            }
          </div> */}

          <div>
            {activeButton === names.loans && 
              <Loans {...props.data.loanData} />
            }
          </div>
        </div>
      ) : (
        <div>Loading...</div>
      )}

    </main>
  )
}

export default Admin;