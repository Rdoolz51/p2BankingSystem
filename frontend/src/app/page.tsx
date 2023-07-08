import {
  LoginButton,
  LogoutButton,
  ProfileButton,
  RegisterButton,
} from "@/components/buttons.component";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { User } from "@/components/user.component";
import styles from './page.module.css'

import Image from "next/image";
import handCardPic from '../../public/handCardPic.jpg'

import CardElement from "@/components/homepage/CardElement";
import News from "@/components/homepage/news/News";
import CardStack from "@/components/homepage/creditCards/CardStack";

async function getData() {
  const pageSize = 5
  const res = await fetch(`https://newsapi.org/v2/everything?domains=wsj.com&apiKey=${process.env.NEWS_API}&pageSize=${pageSize}`);
  const data = await res.json();
  return data;
}

export default async function Home() {
  const session = await getServerSession(authOptions);
  console.log("SESSION HOMEPAGE", session);
  const newsData = await getData();
  // console.log(newsData?.articles);

  return (
    <main >
      <div className={styles.main}> {/* just for development */}
        <LoginButton />
        <RegisterButton />
        <LogoutButton />
        <ProfileButton />
      </div>

      <div className={styles.content}>

        <div className={styles.cardStackElement}>
          <CardStack />
        </div>

        {/* <div className={styles.imageContainer}>
          <Image alt="home-image" src={handCardPic} className={styles.image} />
        </div> */}

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

      {/* <div className={styles.cardStackElement}>
        <CardStack />
      </div> */}

    </main>
  );
}