import React, { useState } from "react";
import styles from '@/components/addAccount/AddAccount.module.css'
import { useSession } from "next-auth/react";
import Modal from "react-modal";

const AddAccount = () => {
    const session = useSession();
    const userToken = session.data?.user.token;
    const [isOpen, setIsOpen] = useState(false);
    const [type, setType] = useState('');
    const [pin, setPin] = useState('');
    
    const accountNumberGen = () => {
        return Math.floor(Math.random() * 1e16).toString().padStart(14, '0');
    }
    
    const handleSubmit = async (event) => {
        event.preventDefault();
        toggleModal();

        const user = {id:session.data?.user.id, firstName:session.data?.user.firstName, lastName:session.data?.user.lastName};

        const data = {
            user: user,
            balance: '0.00',
            accountNumber:accountNumberGen(),
            pin: pin,
            type: {
                type:type
            } 
                
        };

        console.log(data);

       try {
            const response = await fetch(`${process.env.API_URL}/mybank/accounts`, {
                    method: 'POST',
                    headers: {
                            Authorization: 'Bearer ' + userToken,
                            'Content-Type': 'application/json',
                        },

                        body: JSON.stringify(data)
                    });
                    
                    if (!response.ok) {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }
                        
                        const responseData = await response.json();
                        console.log(responseData);
                        } catch (error) {
                                console.error('There was a problem with the fetch operation:', error);
                            }
    }

    const handleChange = (event) => {            
        setType(event.target.value)
    }

    const toggleModal = () => setIsOpen(!isOpen);

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
                    <select name="type" id="accType" required onChange={handleChange}>
                        <option hidden value="">Account Type</option>
                        <option value="Checking">Checking</option>
                        <option value="Savings">Savings</option>
                        <option value="Travel">Travel</option>
                    </select>
                    <input type="password" placeholder="4-Digit PIN" required maxLength={4} minLength={4} onChange={(event) => setPin(event.target.value)}/>
                    <button type="submit" className={styles.submitButton}>Submit</button>
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
