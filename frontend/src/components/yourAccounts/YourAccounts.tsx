import styles from './YourAccounts.module.css';



interface AccountProps {

  type: {
    type:string;
  };


  user: {
    firstName: string;
  };
 
  balance: any;
}

const YourAccounts: React.FC<AccountProps> = (props) => {
  if(!props.user || !props.type || !props.balance) {
    return <div></div>
  }
  console.log(props);
  
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
        <h2 className={styles.accountType}>{props.user.firstName}'s {props.type.type} : {accountNumber}</h2>  
        <h2 className={styles.balance}>Current Balance: <span>${(parseInt(props.balance).toFixed(2))}</span></h2>
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