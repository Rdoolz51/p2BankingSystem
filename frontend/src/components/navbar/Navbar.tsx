'use client'
import { useEffect, useState } from 'react';
import Link from 'next/link';
import styles from './Navbar.module.css';
import { FaUser } from "react-icons/fa6";
import Modal from 'react-modal';
import Image from 'next/image';
import pursueLogo from '../../../public/pursueLogo.png'
import { signIn, signOut, useSession } from "next-auth/react";
import { useRouter } from 'next/navigation';


interface UserProps {
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  income: string;
}


const Navbar: React.FC<UserProps> = ({firstName, lastName, email, phoneNumber, income}) => {

  const [isOpen, setIsOpen] = useState(false)
  const session = useSession();
  const router = useRouter();

  const entryHandler = async () => {
    if(session.data) {
      await signOut();
    } else{
    router.push(`${process.env.NEXTAUTH_URL}/auth/login`)
    }
  }

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
              shouldCloseOnOverlayClick={true}
              onRequestClose={toggleModal}
              shouldCloseOnEsc={true}
            >
              <h2>User Information</h2>
              <p>First Name: {firstName}</p>
              <p>Last Name: {lastName}</p>
              <p>Email: {email}</p>
              <p>Phone Number: {phoneNumber}</p>
              <p>Yearly Income: ${income}</p>
              
              <div><a onClick={() => entryHandler()} className={styles.signOut}>Sign In/Out</a></div>
            </Modal>
          </li>
        </div>
      </ul>
    </nav>
  );
};

export default Navbar;