import styles from './YourAccounts.module.css';
import { useState } from 'react';



interface AccountProps {
  accounts: {
    type: {
      type: string;
    };
    user: {
      firstName: string;
    };
    balance: any;
  }[];
}

const YourAccounts: React.FC<AccountProps> = (props) => {

  const [accountData, setAccountData] = useState(Object.values(props));

  console.log("6666666666666", accountData)
  const depositMoney = (amount: number) => {};
  const withdrawMoney = (amount: number) => {};
  const transferMoney = (amount: number) => {};
  


  return (
    <div className={styles.accountContainer}>
      {accountData.map((account, index) => (
        <div key={index} className={styles.divDiv}>
          <div className={styles.leftContainer}>
            <h2 className={styles.accountType}>{account.user.firstName}'s {account.type.type}</h2>  
            <h2 className={styles.balance}>Current Balance: <span>${parseInt(account.balance).toFixed(2)}</span></h2>
            <div className={styles.buttonContainer}>
              <div className={styles.button} onClick={() => depositMoney(10)}>Deposit $10</div>
              <div className={styles.button} onClick={() => withdrawMoney(10)}>Withdraw $10</div>
              <div className={styles.button} onClick={() => transferMoney(10)}>Transfer $10</div>
            </div>
          </div>
          <div className={styles.transactionContainer}>
            {/* <h2>Recent Transactions</h2> */}
          </div>
        </div>
      ))}
    </div>
  );
};

export default YourAccounts;
