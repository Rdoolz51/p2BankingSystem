'use client'
import { useState } from 'react';
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

const submitCardApp = async (data:Object, token:String) => {
  try{
    const res = await fetch(`${process.env.API_URL}/mybank/cc-app`, {
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

const CardApp: React.FC<CardAppProps> = (props) => {
  const session = useSession();
  const user = session.data?.user;
  const [modalIsOpen, setModalIsOpen] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    const userToken = session?.data?.user?.token;
    const data = {
      creditLimit: '1000',
      interestRate: '.2',
      annualFee: '500'
    }
    const res = await submitCardApp(data, userToken)
    if(res) {
      setModalIsOpen(false)
    } else {
      console.error('oh noo\'s')
    }
  };

  console.log('PPPPPPPPPPPPPPPPPP', props);

  return (
    <div>
      <button onClick={() => setModalIsOpen(true)}>Apply for a Card</button>

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
          <input className={styles.formInput} type="text" id="legalName" value={user?.firstName + user.lastName} required/>
          <input className={styles.formInput} type="text" id="address" value={props?.address.street} required/>
          <input className={styles.formInput} type="text" id="addressCity" value={props?.address.city} placeholder="City" required/>
          <input className={styles.formInput} type="text" id="addressState" value={props?.address.state.name} placeholder="State" required/>
          <input className={styles.formInput} type="text" id="zipCode" value={props?.address.zip.zipCode} placeholder="State" required/>
          <input className={styles.formInput} type="text" id="income" value={"$ " + props?.income + " / year"} placeholder="Yearly Income" required/>
          <input className={styles.formButton} type="submit" value="Submit Application" />
        </form>
      </Modal>
    </div>
  );
};

export default CardApp;
