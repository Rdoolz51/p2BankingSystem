'use client'
import { useEffect, useState } from 'react';
import styles from './MyBankHome.module.css'

import YourAccounts from '../yourAccounts/YourAccounts';
import YourCards from '../yourCards/YourCards';
import { useSession } from 'next-auth/react';
import YourLoans from '../yourLoans/YourLoans';
import AddAccount from '../addAccount/AddAccount';
import CardApp from '../cardApp/CardApp';
import Login from '@/app/auth/login/page';
import { useRouter } from 'next/navigation';

import RecentTransactions from '../recentTransfers/RecentTransactions';

const names = {
  accounts: 'accounts',
  cards: 'cards',
  loans: 'loans',
  transactions: 'transactions'
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
  const router = useRouter();
  
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
  console.log("5555555555555555",data)
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

        <button name={names.transactions}
          onClick={handlerSelector} 
          className={activeButton === names.transactions ? styles.activeButton : ''} 
          disabled={activeButton === names.transactions}  
        >
          Recent Transactions
        </button>
      </div>
      {data && session &&
      <div>
        {activeButton === names.accounts && 
          <YourAccounts {...data} />
        }
        {activeButton === names.accounts &&
        <AddAccount />
        }
      </div>
      }

      {!data && 
      <div>
      {activeButton === names.accounts &&
        router.push(`${process.env.NEXTAUTH_URL}/auth/login`)
      }
    </div>
      }

      {data && session &&
        <div>
          {activeButton === names.cards &&
            <YourCards {...data[0]} />
          }
        </div>
      }
      {!data &&
        <div>
          {activeButton === names.cards &&
            router.push(`${process.env.NEXTAUTH_URL}/auth/login`)
          }
        </div>
      }

      {data && session &&
        <div>
          {activeButton === names.loans &&
            <YourLoans {...data[0]} />
          }
        </div>
      }
      {!data &&
        <div>
          {activeButton === names.loans &&
            router.push(`${process.env.NEXTAUTH_URL}/auth/login`)
          }
        </div>
      
      }

      <div>
        {activeButton === names.transactions && 
          <RecentTransactions {...data} />
        }
      </div>
    
    </main>
  )
}

export default MyBankHome;