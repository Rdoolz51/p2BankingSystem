'use client'
import styles from './AtmPinPad.module.css'
import React from 'react'

const AtmPinPad = () => {
    return(
        <div>
            <div className={styles.atmButtons}>
                <button className={styles.atmButton1}>1</button>
                <button className={styles.atmButton2}>2</button>
                <button className={styles.atmButton3}>3</button>
                <button className={styles.atmButton4}>4</button>
                <button className={styles.atmButton5}>5</button>
                <button className={styles.atmButton6}>6</button>
                <button className={styles.atmButton7}>7</button>
                <button className={styles.atmButton8}>8</button>
                <button className={styles.atmButton9}>9</button>
                <button className={styles.atmButtonDot}><span>.</span></button>
                <button className={styles.atmButton0}>0</button>
                <button className={styles.atmButton00}>00</button>
                <button className={styles.atmButtonEnter}><span>Enter</span></button>
                <button className={styles.atmButtonClear}><span>Clear</span></button>
                <button className={styles.atmButtonCancel}><span>Cancel</span></button>
                <button className={styles.atmButtonExtra}></button>
            </div>
        </div>
    );

}
    export default AtmPinPad;