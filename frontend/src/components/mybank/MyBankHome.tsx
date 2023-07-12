'use client'
import { useEffect, useState } from 'react';
import styles from './MyBankHome.module.css'

import YourAccounts from '../yourAccounts/YourAccounts';
import YourCards from '../yourCards/YourCards';
import { useSession } from 'next-auth/react';
import YourLoans from '../yourLoans/YourLoans';
import AddAccount from '../addAccount/AddAccount';

const names = {
  accounts: 'accounts',
  cards: 'cards',
  loans: 'loans',
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

const MyBankHome: React.FC<any> = (props:any) => {

  const [activeButton, setActiveButton] = useState('')
  const [data, setData] = useState(); // State for data
  
  const session = useSession();
  const token = session.data?.user?.token;

  const handlerSelector = async (e:any) => {
    const a = await fetchRoutes(e.target.name, token);
    setData(a)
    setActiveButton(e.target.name)
  }
if(data) {
  console.log(data[0])
}

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
          <YourAccounts {...data[0]} />
        }
        {activeButton === names.accounts &&
        <AddAccount />
        } 
      </div>

      <div>
        {activeButton === names.cards &&
          <YourCards {...data[0]} />
        }
      </div>

      <div>
        {activeButton === names.loans && 
          <YourLoans {...data[0]} />
        }
      </div>
    
    </main>
  )
}

export default MyBankHome;