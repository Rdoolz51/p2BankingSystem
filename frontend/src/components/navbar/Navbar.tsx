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
          <Link legacyBehavior href="/myBank">
            <a>My Bank</a>
          </Link>
        </li>
        <div className={styles.profile}>
        <li>
            <Link href="/profile"><FaUser></FaUser></Link>
        </li>
        </div>
      </ul>
    </nav>
  );
};

export default Navbar;
