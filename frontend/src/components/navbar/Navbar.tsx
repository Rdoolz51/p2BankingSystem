'use client'
import { useState } from 'react';
import Link from 'next/link';
import styles from './Navbar.module.css';
import { FaUser } from "react-icons/fa6";
import Modal from 'react-modal';
import Image from 'next/image';
import pursueLogo from '../../../public/pursueLogo.png'
import {signOut, useSession } from "next-auth/react";
import { useRouter } from 'next/navigation';

interface UserProps {
  phoneNumber: string;
  income: string;
}


const Navbar: React.FC<UserProps> = ({phoneNumber, income}) => {

  const [isOpen, setIsOpen] = useState(false)
  const { data: session, status } = useSession();
  const router = useRouter();

  const entryHandler = async (num) => {
    if(session && !num) {
      await signOut();
      router.refresh();
    } else if(num == 1){
      router.push(`${process.env.NEXTAUTH_URL}/auth/login`)
      toggleModal();
    } else {
      router.push(`${process.env.NEXTAUTH_URL}/auth/register`)
      toggleModal();
    }
  }
  console.log('1111111111111111111111111111111111111111111111111111111111111111111111', session)
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
          {session?.user.role == "Customer" &&
            <Link legacyBehavior href="/mybank">
            <a>My Bank</a>
          </Link>
          }
          {session?.user.role == "Admin" &&
            <Link legacyBehavior href="/admin">
            <a>Admin Dashboard</a>
          </Link>
          }

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
              >
              <div>
                <h2>User Information</h2>
                {session &&
                  <>
                    <p>First Name: {session.user.firstName}</p>
                    <p>Last Name: {session.user.lastName}</p>
                    <p>Email: {session.user.email}</p>
                  </>
                }
                  {session &&
                  <div><a onClick={() => entryHandler()} className={styles.signOut}>
                   Sign Out
                   </a></div>
                   }
                   {!session &&
                    <div><a onClick={() => entryHandler(1)} className={styles.signOut}>
                    Sign In
                    </a>
                    <a onClick={() => entryHandler(2)} className={styles.signOut}>
                      Register
                    </a></div>
                   }
              </div>
            </Modal>
          </li>
        </div>
      </ul>
    </nav>
  );
};

export default Navbar;
