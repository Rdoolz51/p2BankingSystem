'use client'
import { useState } from 'react';
import styles from './MyBankHome.module.css'

import YourAccounts from '../yourAccounts/YourAccounts';

const names = {
  accounts: 'accounts',
  cards: 'cards',
  loans: 'loans',
}


const MyBankHome: React.FC<any> = (props:any) => {
  // const [selector, setSelector] = useState('')
  const [activeButton, setActiveButton] = useState('')

  const handlerSelector = (e:any) => {
    setActiveButton(e.target.name)
  }

  console.log(activeButton);

  return (
    <main>

      <div className={styles.buttonContainer}>
        <button name={names.accounts} 
          onClick={handlerSelector} 
          className={activeButton === names.accounts ? styles.activeButton : ''} 
          disabled={activeButton === names.accounts}
        >
          Your Accounts
        </button>

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
      </div>

      <div>
        {activeButton === names.accounts && 
          <YourAccounts {...props} />
        }
      </div>

      <div>
        {activeButton === names.cards &&
          <YourAccounts {...props} />
        }
      </div>

      <div>
        {activeButton === names.loans && 
          <YourAccounts {...props} />
        }
      </div>
    
    </main>
  )
}

export default MyBankHome;