'use client'
import { useSession } from "next-auth/react"
import { redirect } from 'next/navigation'
import { useState } from 'react';

import { approveOrDenyLoan, getPendingLoans } from "@/app/api/admin";

import styles from './Loans.module.css'

const Loans: React.FC<any> = (props:any) => {
  const [loanData, setLoanData] = useState(Object.values(props))

  const session = useSession()
  const token = session?.data?.user?.token

  const handleDeny = async (e:any) => {
    const data = { // yes, I'm hardcoding the values. I'm tired
      status: {
        id: 3,
        status: "Denied"
      },
      userId: e.target.name,
      appId: e.target.value
    }

    const res = await approveOrDenyLoan(token, data)
    const newData = await getPendingLoans(token);
    setLoanData(newData)
  }

  const handleApprove = async (e:any) => {
    const data = { // yes, I'm hardcoding the values. I'm tired
      status: {
        id: 3,
        status: "Approve"
      },
      userId: e.target.name,
      appId: e.target.value
    }

    const res = await approveOrDenyLoan(token, data)
    const newData = await getPendingLoans(token);
    setLoanData(newData)
  }

  return (
    <div className={styles.accountContainer}>

      {loanData.map((loan:any) => {
        return (
          <div className={styles.leftContainer} key={loan.loanID}>
            <h2 className={styles.accountType}>Applicant: {loan.user.firstName} {loan.user.lastName}</h2>  
            <h2 className={styles.accountNumber}>Loan Type: {loan.type.type}</h2>
            <h2 className={styles.accountNumber}>Loan Amount: ${loan.loanAmount}</h2>
            <h2 className={styles.accountNumber}>Interest Rate: {loan.interestRate}</h2>
            <div className={styles.buttonContainer}>
              <button className={styles.button} name={loan.user.id} value={loan.loanID} style={{ backgroundColor: '#8b2020' }} onClick={handleDeny}>Deny Loan</button>
              <button className={styles.button} name={loan.user.id} value={loan.loanID} style={{ backgroundColor: '#16168f' }} onClick={handleApprove}>Approve Loan</button>
            </div>
          </div>
        )
      })}

    </div>
  )
}

export default Loans;