import React, { useState } from "react";
import styles from '@/components/addAccount/AddAccount.module.css'
import { useSession } from "next-auth/react";
import Modal from "react-modal";


const AddAccount = () => {
    // Placeholder function that should represent your API call
    const session = useSession();
    const userToken = session.data?.user.token;
    const [isOpen, setIsOpen] = useState(false)
    const [type, setType] = useState('')


    const createAccount = async () => {

        const url = `${process.env.API_URL}/mybank/accounts`;


        //Generate Account Number
        const accountNumberGen = () => {
            const accountNumber = [];

            for(let i = 0; i < 16; i++) {
                let num = Math.floor(Math.random() * 10);
                accountNumber.push(num);
            }
            return accountNumber.toString();
        }

        // The data to send with your request
        const data = {
            firstName: session.data?.user?.firstName,
            balance: '0.00',
            accountNumber:accountNumberGen(),
            pin: '1123',
            type: type,

            // Insert the data you need to create an account
        };

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    Authorization: 'Bearer' + userToken,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            // Use the returned data as needed
            const responseData = await response.json();
            console.log(responseData);
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
        }
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Handle form submission here
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
            >
                <div>
                <button onClick={toggleModal} className={styles.closeButton}>X</button>
                <form onSubmit={handleSubmit} className={styles.modalForm}>
                    <input type="text" id="PIN"/>
                    <input type="text" />
                    <select name="type" id="accType" onChange={(event) => setType(event.target.value)}>
                        <option hidden value="">Account Type</option>
                        <option value="Checking">Checking</option>
                        <option value="Savings">Savings</option>
                        <option value="Travel">Travel</option>
                    </select>
                    <input type="text" />
                    <input type="text" />
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