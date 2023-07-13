import styles from './YourLoans.module.css';



interface LoanProps {
loanAmount: string;
type: {
    type: string;
};
loanBalance: string;
interestRate: string;
user: {
    firstName: string;
    lastName: string;
}

}

const YourLoans: React.FC<LoanProps> = (props) => {


  return (
    <div className={styles.accountContainer}>
      <div className={styles.leftContainer}>
        <h2 className={styles.accountType}>{props.user.firstName}'s {props.type.type} Loan</h2>  
        <h2 className={styles.balance}>Initial Loan Amount: <span>${(parseInt(props.loanAmount).toFixed(2))}</span></h2>
        <h2 className={styles.balance}>Current Balance: <span>${(parseInt(props.loanBalance).toFixed(2))}</span></h2>
        <h2 className={styles.balance}>Interest Rate: <span>${(props.interestRate)}%</span></h2>
      </div>
      <div className={styles.buttonContainer}>
          <div className={styles.button}>Make Payment</div>
        </div>
      <div className={styles.transactionContainer}>
        {/* <h2>Recent Transactions</h2> */}
      </div>
    </div>
  );
}

export default YourLoans;