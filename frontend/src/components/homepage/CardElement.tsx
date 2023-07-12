/* eslint-disable @next/next/no-img-element */
'use client'
import { useSession } from "next-auth/react"


import styles from './Card.module.css'
import { content } from "./content";

const CardElement = () => {
  const { data: session, status } = useSession();
  // console.log("AAAAAAAAAAAAAAAA", session);

  return (
    <div className={styles.cardContainer}>
      {content.map((c) => {
        return (
          <div className={styles.card} key={c.id}>
            <h2 className={styles.accountType}>{c.content}</h2>
          </div>
        )
      })}
    </div>
  )
}

export default CardElement;