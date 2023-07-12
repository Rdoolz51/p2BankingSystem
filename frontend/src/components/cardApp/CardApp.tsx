'use client'
import React from "react";
import styles from './CardApp.module.css';

const CardApp = () => {
  const handleSubmit = (event) => {
    event.preventDefault();
    // TODO: Add your form submission logic here.
    alert("Form submitted");
  };

  return (
    <div className={styles.formContainer}>
        <h1 className={styles.title}>Credit Card Application</h1>
      <form onSubmit={handleSubmit}>
        <input className={styles.formInput} type="text" id="legalName" placeholder="Full Legal Name" required/>
        <input className={styles.formInput} type="text" id="address" placeholder="Street Address" required/>
        <input className={styles.formInput} type="text" id="addressCity" placeholder="City" required/>
        <input className={styles.formInput} type="text" id="addressState" placeholder="State" required/>
        <input className={styles.formInput} type="text" id="dateOfBirth" placeholder="MM/DD/YYYY" required/>
        <input className={styles.formInput} type="text" id="income" placeholder="Yearly Income" required/>
        <input className={styles.formButton} type="submit" value="Submit Application" />
      </form>
    </div>
  );
};

export default CardApp;
