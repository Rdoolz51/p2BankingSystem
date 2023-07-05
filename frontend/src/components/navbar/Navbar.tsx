import Link from 'next/link';
import styles from './Navbar.module.css';
import { FaUser } from "react-icons/fa6";

const Navbar = () => {
  return (
    <nav className={styles.nav}>
      <div className={styles.companyName}>
        <h1>Company Name</h1>
      </div>
      <ul>
        <li>
          <Link href="/">
            <p>Home</p>
          </Link>
        </li>
        <li>
          <Link href="/myBank">
            <p>My Bank</p>
          </Link>
        </li>
      </ul>
      <ul className={styles.profile}>
        <li>
            <Link href="/profile"><FaUser></FaUser></Link>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;