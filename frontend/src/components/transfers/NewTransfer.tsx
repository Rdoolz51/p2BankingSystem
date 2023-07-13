import React, { useState } from "react";
import styles from './NewTransfer.module.css'
import { useSession } from "next-auth/react";
import Modal from "react-modal";

const submitTransfer = async (data:Object, token:String) => {
  try{
    const res = await fetch(`${process.env.API_URL}/mybank/transfers`, {
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


const submitTransferOther = async (data:Object, token:String) => {
  console.log('aaaaaaaaaaaaa ', data);
  try{
    const res = await fetch(`${process.env.API_URL}/transfers`, {
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
      console.error('Something went wrong other (/transfers) ')
      return null;
    }
  } catch (e) {
    console.error('Something went wrong doing other transfer stuff: ', e)
    return null;
  }
}

const searchAccountsByEmail = async (email:String) => {
  try{
    const res = await fetch(`${process.env.API_URL}/transfers/${email}`)

    if(res.ok) {
      const data = await res.json();
      return data;
    } else {
      console.error('Something went wrong other (/transfers) ')
      return null;
    }
  } catch (e) {
    console.error('Something went wrong doing other transfer stuff: ', e)
    return null;
  }
}

//-----------------------------------
const NewTransfer = (props:any) => {
  const session = useSession();
  const userToken = session?.data?.user?.token;
  const { accountID } = props;
  console.log('pppppppppp >> ', props);

  const [isOpen, setIsOpen] = useState(false);
  const [type, setType] = useState('')
  const [email, setEmail] = useState('');
  const [amount, setAmount] = useState('');
  const [toAccount, setToAccount] = useState('')
  const [availableAcc, setAvailableAcc] = useState(null)
  const [error, setError] = useState('');
  
  const handleSubmit = async (event:any) => {
    event.preventDefault();
    const data = {
      fromAccountId: accountID,
      toAccountId: toAccount, //hardcoded for now
      amount: `${amount}`
    };

    // const res = await fetchAccountByEmail(email, userToken)


    if(data.fromAccountId != data.toAccountId) {
      const res = await submitTransfer(data, userToken);
      if(res) {
        toggleModal();
        
      } else {
        setError('Insufficient Funds!')
      }
    } else {
      setError("The account ID entered is the same as the sender account.")
    }
  }

  //transfers to other accounts
  const handleSubmitOther = async (event:any) => {
    const intAcc = parseInt(toAccount)
    event.preventDefault();
    const data = {
      fromEmail: session.data?.user?.email,
      toEmail: email,
      fromAcct: accountID,
      toAcct: intAcc, //hardcoded for now
      amount: `${amount}`
    };

    const res = await submitTransferOther(data, userToken);

    if(res) {
      toggleModal();
      
    } else {
      setError('Insufficient Funds!')
    }
  }

  const handleEmailSearch = async (event:any) => {
    const res = await searchAccountsByEmail(event.target.value)
    setEmail(event.target.value)
    if(res) {
      setAvailableAcc(res.accountIds)
    } else {
      setAvailableAcc(null)
    }
    
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
          <button className={styles.button2} onClick={() => setType('myAccount')} >My Accounts</button>
          <button className={styles.button2} onClick={() => setType('othAccount')} >Other Accounts</button>
          {type == 'myAccount' ? (
            <form onSubmit={handleSubmit} className={styles.modalForm}>
              <h1 className={styles.formTitle}>Transfer To <strong>My Account</strong></h1>
              <h5 className={styles.errorText}>{error}</h5>
              <input type="text" placeholder="Account Number" required onChange={(event) => setToAccount(event.target.value)}/>
              <input type="number" placeholder="Amount" onChange={(event) => setAmount(event.target.value)}/>
              <button type="submit" className={styles.button}>Submit</button>
            </form>
          ) : (
            <form onSubmit={handleSubmitOther} className={styles.modalForm}>
              <h1 className={styles.formTitle}>Transfer To <strong>Other Accounts</strong></h1>
              <h5 className={styles.errorText}>{error}</h5>
              <input type="text" placeholder="Email" required onChange={handleEmailSearch}/>
              <select value={toAccount} onChange={(event) => setToAccount(event.target.value)} className={styles.option}>
                {availableAcc ? (
                  availableAcc.map((acc:any) => (
                    <option key={acc} value={acc} >
                      {acc}
                    </option>
                  ))
                ) : (
                  <option value="">No available accounts</option>
                )}
              </select>
              <input type="number" placeholder="Amount" onChange={(event) => setAmount(event.target.value)}/>
              
              
              <button type="submit" className={styles.button}>Submit</button>
            </form>
          )}
        </div>
      </Modal>
      <button className={styles.button} onClick={toggleModal}>
        Transfer Funds
      </button>
    </div>
  );
}

export default NewTransfer;
