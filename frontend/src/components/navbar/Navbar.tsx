import Link from 'next/link';
import styles from './Navbar.module.css';
import { FaUser } from "react-icons/fa6";

const Navbar = () => {
  return (
    <nav className={styles.nav}>
      <Link legacyBehavior href={"/"}>
        <a><h1 className={styles.companyName}>Nameless Bank</h1></a>
      </Link>
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
