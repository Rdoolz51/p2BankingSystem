import React, { useState } from "react";
import styles from './NewTransfer.module.css'
import { useSession } from "next-auth/react";
import Modal from "react-modal";

// const fetchAccountByEmail = async (email:String, token:String) => {
//   try{
//     const res = await fetch(`${process.env.API_URL}/mybank/${active}`, {
//       headers: {
//         Authorization: 'Bearer ' + token,
//         'Content-Type': 'application/json'
//       }
//     }) 
//       const response = await res.json();
//       return response;
//     } catch(e) {
//       console.log("ERROR" , e)
//     }
// }

const submitTransfer = async (data:Object, token:String) => {
  try{
    const res = await fetch(`${process.env.API_URL}/mybank/transfer`, {
      method: 'PUT',
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


const NewTransfer = (props:any) => {
  const session = useSession();
  const userToken = session?.data?.user?.token;
  const { accountID } = props;

  const [isOpen, setIsOpen] = useState(false);
  const [email, setEmail] = useState('');
  const [amount, setAmount] = useState('');
  const [toAccount, setToAccount] = useState('')
  
  const handleSubmit = async (event:any) => {
    event.preventDefault();
    const data = {
      fromAccountId: accountID,
      toAccountId: toAccount, //hardcoded for now
      amount: `${amount}`
    };

    // const res = await fetchAccountByEmail(email, userToken)

    const res = await submitTransfer(data, userToken);
    console.log('REEEEEEEEEEEEEs >>> ', res);


    // toggleModal();




    console.log(data);
  }

  const toggleModal = () => setIsOpen(!isOpen);

  return (
    <div className={styles.container}>
      <Modal 
        isOpen={isOpen}
        contentLabel="New Transfer"
        className={styles.modalContent}
        overlayClassName={styles.modalOverlay}
        shouldCloseOnOverlayClick={true}
        onRequestClose={toggleModal}
        ariaHideApp={false}
    >
        <div>
          <button onClick={toggleModal} className={styles.closeButton}>X</button>
          <form onSubmit={handleSubmit} className={styles.modalForm}>
              <h1 className={styles.formTitle}>Transfer Funds</h1>
              <input type="text" placeholder="Account Number" required onChange={(event) => setToAccount(event.target.value)}/>
              <input type="number" placeholder="Amount" onChange={(event) => setAmount(event.target.value)}/>
              <button type="submit" className={styles.submitButton}>Submit</button>
          </form>
        </div>
      </Modal>
      <button className={styles.addButton} onClick={toggleModal}>
        Transfer Funds
      </button>
    </div>
  );
}

export default NewTransfer;
