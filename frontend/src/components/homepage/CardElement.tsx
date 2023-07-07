/* eslint-disable @next/next/no-img-element */
'use client'
import Link from "next/link";
import Image from "next/image";
import Card from 'react-bootstrap/Card';

import styles from './Card.module.css'
import { content } from "./content";

const CardElement = () => {
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