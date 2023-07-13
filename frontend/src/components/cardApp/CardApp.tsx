'use client'
import { useEffect, useState } from 'react';
import styles from './CardApp.module.css';
import { useSession } from 'next-auth/react';
import Modal from 'react-modal';

interface CardAppProps {
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

const getUserData = async () => {
  try{
    const res = await fetch(`${process.env.API_URL}/mybank`)

    if(res.ok) {
      const data = await res.json();
      return data;
    }
  } catch (e) {
    console.error('Something went wrong: ', e)
    return null;
  }
}
const CardApp: React.FC<CardAppProps> = (props) => {
  const session = useSession();
  const user = session.data?.user;
  const [modalIsOpen, setModalIsOpen] = useState(false);
  

  const handleSubmit = (event) => {
    event.preventDefault();
  };
  

  return (
    <div>
      <button className={styles.button} onClick={() => setModalIsOpen(true)}>Apply for a Card</button>

      <Modal 
        isOpen={modalIsOpen} 
        onRequestClose={() => setModalIsOpen(false)}
        contentLabel="Card Application Form"
        className={styles.formContainer}
        overlayClassName={styles.overlay}
      >
        <h1 className={styles.title}>Credit Card Application</h1>
        <h4 className={styles.subtitle}>Please verify that all information is correct before submitting application.</h4>
        <form onSubmit={handleSubmit}>
        <input className={styles.formInput} type="text" id="legalName" value={user?.firstName + " " + user?.lastName} required/>
        <input className={styles.formInput} type="text" id="address" value={props?.user?.address?.street} required/>
        <input className={styles.formInput} type="text" id="addressCity" value={props?.user?.address?.city} placeholder="City" required/>
        <input className={styles.formInput} type="text" id="addressState" value={props?.user?.address?.state.name} placeholder="State" required/>
        <input className={styles.formInput} type="text" id="zipCode" value={props?.user?.address?.zip?.zipCode} placeholder="State" required/>
        <input className={styles.formInput} type="text" id="income" value={"$ " + props?.user?.income + " / year"} placeholder="Yearly Income" required/>
        <input className={styles.formButton} type="submit" value="Submit Application" />
        </form>
      </Modal>
    </div>
  );
};

export default CardApp;
