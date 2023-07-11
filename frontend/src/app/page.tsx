import {
  LoginButton,
  LogoutButton,
  ProfileButton,
  RegisterButton,
} from "@/components/buttons.component";
import { getServerSession } from "next-auth/next"
import { authOptions } from "@/lib/auth";
import styles from './page.module.css'

import Image from "next/image";
import CardElement from "@/components/homepage/CardElement";
import News from "@/components/homepage/news/News";


export default async function Home() {
  const session = await getServerSession(authOptions);
  console.log("SESSION HOMEPAGE >> ", session?.user);

  return (
    <main >
      <div className={styles.main}> {/* just for development */}
        <LoginButton />
        <RegisterButton />
        <LogoutButton />
        <ProfileButton />
      </div>

      <div className={styles.content}>
        <div className={styles.imageContainer}>
        </div>

        <div className={styles.headerContainer}>
          <h1>
            The Best Banking System. Ever.
          </h1>
          <h3>
            Lorem ipsum more ipsum than lorem I do not know what else to do to put here but I need words 
          </h3>
        </div>
      </div>

      <div>
        <CardElement />
      </div>

      <div className={styles.newsElement}>
        <h1>Recent News</h1>
        <News />
      </div>

    </main>
  );
}