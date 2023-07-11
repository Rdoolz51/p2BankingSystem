'use client'
import styles from './CardStack.module.css'
import Image from 'next/image';
import BryanCard from '../../../../public/BryanCard2.jpg'

const CardStack = () => {
  return (
    <div className={styles.container}>

      {/* Might come back to transition the images (z-index) better */}

      <div className={styles.imgContainer}>
        <Image alt='card' src={BryanCard} />
        <Image alt='card' src={BryanCard} />
        <Image alt='card' src={BryanCard} />
      </div>

    </div>
  )
}

export default CardStack;