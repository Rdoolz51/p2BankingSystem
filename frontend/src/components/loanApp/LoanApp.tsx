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

const LoanApp: React.FC<LoanAppProps> = (props) => {
  const session = useSession();
  const user = session.data?.user;
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const handleSubmit = (event) => {
    event.preventDefault();
  };
  
    return (
      <div>
      <button onClick={() => setModalIsOpen(true)}>Apply for a Card</button>

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
        <input className={styles.formInput} type="text" id="legalName" value={user?.firstName + user.lastName} required/>
        <input className={styles.formInput} type="text" id="address" value={props.user.address.street} required/>
        <input className={styles.formInput} type="text" id="addressCity" value={props.user.address.city} placeholder="City" required/>
        <input className={styles.formInput} type="text" id="addressState" value={props.user.address.state.name} placeholder="State" required/>
        <input className={styles.formInput} type="text" id="zipCode" value={props.user.address.zip.zipCode} placeholder="State" required/>
        <input className={styles.formInput} type="text" id="income" value={"$ " + props.user.income + " / year"} placeholder="Yearly Income" required/>
        <input className={styles.formButton} type="submit" value="Submit Application" />
        </form>
      </Modal>
    </div>
  );
};
  export default LoanApp; 


