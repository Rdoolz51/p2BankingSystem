import { authOptions } from "@/lib/auth";
import { getServerSession } from "next-auth";

import styles from './Register.module.css'
import { RegisterForm } from "./form";

export default async function Register() {
  const session = await getServerSession(authOptions);
  console.log("register Session >>> ", session);

  return (
    // <main className={styles.main}>
    <div className={styles.tryingMyBest}>
      <div className={styles.wrapper}>
        <RegisterForm />
      </div>
    </div>

    // </main>
  )
}