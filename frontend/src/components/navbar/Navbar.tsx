'use client'
import { useState } from 'react';
import Link from 'next/link';
import styles from './Navbar.module.css';
import { FaUser } from "react-icons/fa6";
import Modal from 'react-modal';
import Image from 'next/image';
import pursueLogo from '../../../public/pursueLogo.png';

interface UserProps {
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  yearlyIncome: number;
}


const Navbar: React.FC<UserProps> = ({ firstName, lastName, email, phoneNumber, yearlyIncome }) => {

  const [isOpen, setIsOpen] = useState(false)

  const toggleModal = () => setIsOpen(!isOpen);

  return (
    <nav className={styles.nav}>
      <div className={styles.logoContainer}>
        <Link legacyBehavior href={"/"}>
          <a><h1 className={styles.companyName}>Pursue</h1></a>
        </Link>
        <Image className={styles.logo} src={pursueLogo} alt="Pursue logo" />
      </div>
      <ul>
        <li>
          <Link legacyBehavior href="/mybank">
            <a>My Bank</a>
          </Link>
        </li>
        <div className={styles.profile}>
          <li>
            <div> <a onClick={toggleModal}><FaUser></FaUser> </a> </div>
            <Modal
              isOpen={isOpen}
              contentLabel="User Information"
              className={styles.modalContent}
              overlayClassName={styles.modalOverlay}
            >
              <h2>User Information</h2>
              <p>First Name: {firstName}</p>
              <p>Last Name: {lastName}</p>
              <p>Email: {email}</p>
              <p>Phone Number: {phoneNumber}</p>
              <p>Yearly Income: ${yearlyIncome}</p>
              <div><a className={styles.signOut} href="/auth/login">Sign Out</a></div>
            </Modal>
          </li>
        </div>
      </ul>
    </nav>
  );
};

export default Navbar;