import styles from './YourAccounts.module.css';



interface AccountProps {

  type:string;


  firstName: string;
 
 balance: number;
}

const YourAccounts: React.FC<AccountProps> = ({firstName, type, balance}) => {

let accountNumber = '12345312454325'
  const depositMoney = (amount: number) => {
 
  }

  const withdrawMoney = (amount: number) => {
  
  }
  
  const transferMoney = (amount: number) => {
   
  }

  return (
    <div className={styles.accountContainer}>
      <div className={styles.leftContainer}>
        <h2 className={styles.accountType}>{firstName}'s {type} : {accountNumber}</h2>  
        <h2 className={styles.accountNumber}>Account Number: {accountNumber}</h2>
        <h2 className={styles.balance}>Current Balance: <span>${balance.toFixed(2)}</span></h2>
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
  );
}

export default YourAccounts;