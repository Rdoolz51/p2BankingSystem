'use client'
import React, { useState } from "react";
import styles from '@/components/loanApp/LoanApp.module.css'
import { useSession } from "next-auth/react";
import Modal from "react-modal";
interface LoanAppProps {
  cards: {
    user: {
      address: {
        street:string;
        city: string;
        state: {
          name: string;
        };   
        zip: {
          zipCode: string;
        }
      }
      income: string;
    }
  }[]
}

const submitLoanApp = async (data:Object, token:String) => {
  try{
    const res = await fetch(`${process.env.API_URL}/mybank/loan-app`, {
      method: 'POST',
      headers: {
        Authorization: 'Bearer ' + token,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data),
    })

    if(res.ok) {
      const data = await res.json();
      return data;
    } else {
      console.error('Something went wrong (/transfer) ')
      return null;
    }
  } catch (e) {
    console.error('Something went wrong doing transfer stuff: ', e)
    return null;
  }
}


const LoanApp: React.FC<LoanAppProps> = (props) => {
  const session = useSession();
  const user = session.data?.user;
  const [modalIsOpen, setModalIsOpen] = useState(false);
  
  const handleSubmit = async (e:any) => {
    e.preventDefault();
    const userToken = session?.data?.user?.token;
    const data = { //hardcode?
      amount: '10000',
      type: {
        id: 1,
      },
      interestRate: '15',

    }
    const res = await submitLoanApp(data, userToken)
    if(res) {
      setModalIsOpen(false)
    } else {
      console.error('oh noo\'s')
    }
  };

  console.log('ppppppppasdasdasdasdasd ', props);
  
    return (
      <div>
      <button className={styles.button} onClick={() => setModalIsOpen(true)}>Apply for a Loan</button>

      <Modal 
        isOpen={modalIsOpen} 
        onRequestClose={() => setModalIsOpen(false)}
        contentLabel="Loan Application Form"
        className={styles.formContainer}
        overlayClassName={styles.overlay}
      >
        <h1 className={styles.title}>Loan Application</h1>
        <h4 className={styles.subtitle}>Please verify that all information is correct before submitting application.</h4>
        <form onSubmit={handleSubmit}>
          <input className={styles.formInput} disabled type="text" name="legalName" value={user?.firstName + user?.lastName} required/>
          <input className={styles.formInput} disabled type="text" name="address" value={props?.user?.address?.street} required/>
          <input className={styles.formInput} disabled type="text" name="addressCity" value={props?.user?.address?.city} placeholder="City" required/>
          <input className={styles.formInput} disabled type="text" name="addressState" value={props?.user?.address?.state?.name} placeholder="State" required/>
          <input className={styles.formInput} disabled type="text" name="zipCode" value={props?.user?.address?.zip?.zipCode} placeholder="State" required/>
          <input className={styles.formInput} disabled type="text" name="income" value={"$ " + props?.user?.income + " / year"} placeholder="Yearly Income" required/>
          <input className={styles.formButton} type="submit" value="Submit Application" />  
        </form>
      </Modal>
    </div>
  );
};
  export default LoanApp; 


