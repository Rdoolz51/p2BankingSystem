'use client'
import { useState } from 'react';
import styles from './MyBankHome.module.css'

import YourAccounts from '../yourAccounts/YourAccounts';


const MyBankHome: React.FC<any> = (props:any) => {
  const [selector, setSelector] = useState('')
  const [activeButton, setActiveButton] = useState('')

  const handlerSelector = (e:any) => {
    setActiveButton(e.target.name)
  }

  console.log(activeButton);

  return (
    <main>

      <div className={styles.buttonContainer}>
        <button name='accounts' onClick={handlerSelector} className={activeButton === 'accounts' ? styles.activeButton : ''} >
          Your Accounts
        </button>

        <button name='cards' onClick={handlerSelector} className={activeButton === 'cards' ? styles.activeButton : ''} >
          Your Cards
        </button>

        <button name='loans' onClick={handlerSelector} className={activeButton === 'loans' ? styles.activeButton : ''} >
          Your Loans
        </button>
      </div>

      <div>
        <YourAccounts {...props} />
      </div>

      <div>
        <YourAccounts {...props} />
      </div>

      <div>
        <YourAccounts {...props} />
      </div>
    
    </main>
  )
}

export default MyBankHome;