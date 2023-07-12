import React, { useEffect, useState } from "react";
import styles from '@/components/addAccount/AddAccount.module.css'
import { useSession } from "next-auth/react";
import Modal from "react-modal";

const AddAccount = () => {
    // Placeholder function that should represent your API call
    const session = useSession();
    const userToken = session.data?.user.token;
    const [isOpen, setIsOpen] = useState(false);
    const [type, setType] = useState('');
    const [user, setUser] = useState({firstName:'', lastName:''});
    const [pin, setPin] = useState('');
    const [data, setData] = useState({user:{}, balance:'', accountNumber:'', pin:'', type:''})    
    
    const createAccount = async () => {
        
        const url = `${process.env.API_URL}/mybank/accounts`;
        
        setUser({firstName:session.data?.user.firstName, lastName:session.data?.user.lastName});
        //Generate Account Number
        const accountNumberGen = () => {
            const accountNumber = [];
            
            for(let i = 0; i < 16; i++) {
                let num = Math.floor(Math.random() * 10);
                accountNumber.push(num);
            }
            return accountNumber.toString();
        }
        
        const data = {
            user: user,
            balance: '0.00',
            accountNumber:accountNumberGen(),
            pin: pin,
            type: type,
            
        };

        setData(data)

        // try {
            // const response = await fetch(url, {
                //     method: 'POST',
                //     headers: {
                    //         Authorization: 'Bearer' + userToken,
                    //         'Content-Type': 'application/json',
                    //     },
                    //     body: JSON.stringify(data),
                    // console.log(JSON.stringify(data))
                    // });
                    
                    // if (!response.ok) {
            //     throw new Error(`HTTP error! status: ${response.status}`);
            // }
            
            // Use the returned data as needed
            // const responseData = await response.json();
            // console.log(responseData);
            // } catch (error) {
                //     console.error('There was a problem with the fetch operation:', error);
                // }
            }
            
            const handleSubmit = async (event) => {
                event.preventDefault();
                toggleModal();
                console.log("1111111111111111111111111111111111111",(data))
                
            }
            const handleChange = async (event) => {            
                setType(event.target.value)
              
            }
            const toggleModal = () => setIsOpen(!isOpen);
            
            console.log(type)
    return (
        <div className={styles.container}>
            <Modal 
                isOpen={isOpen}
                contentLabel="Add Account"
                className={styles.modalContent}
                overlayClassName={styles.modalOverlay}
                shouldCloseOnOverlayClick={true}
                onRequestClose={toggleModal}
                ariaHideApp={false}
            >
                <div>
                <button onClick={toggleModal} className={styles.closeButton}>X</button>
                <form onSubmit={handleSubmit} className={styles.modalForm}>
                    <h1 className={styles.formTitle}>Add Account</h1>
                    <select name="type" id="accType" onChange={handleChange}>
                        <option hidden value="">Account Type</option>
                        <option value="Checking">Checking</option>
                        <option value="Savings">Savings</option>
                        <option value="Travel">Travel</option>
                    </select>
                    <input type="password" placeholder="4-Digit PIN" maxLength={4} minLength={4} onChange={(event) => setPin(event.target.value)}/>
                    <button type="submit" className={styles.submitButton} onSubmit={handleSubmit}>Submit</button>
                </form>
                </div>
            </Modal>
            <button className={styles.addButton} onClick={toggleModal}>
                Add Account
            </button>
        </div>
       
    );
}

export default AddAccount;