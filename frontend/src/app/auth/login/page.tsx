import { authOptions } from "@/lib/auth";
import { getServerSession } from "next-auth";

import { LoginForm } from "./form";
import styles from './Login.module.css'

import { Card } from "react-bootstrap";

export default async function Login() {
  const session = await getServerSession(authOptions);
  console.log("register Session >>> ", session);

  return (
    // <main className={styles.main}>
    <div className={styles.tryingMyBest}>
      <div className={styles.wrapper}>
        <LoginForm />
      </div>
    </div>

    // </main>
  )
}