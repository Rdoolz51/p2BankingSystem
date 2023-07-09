/* eslint-disable @next/next/no-img-element */
"use client";

import Link from 'next/link';
import { useSearchParams, useRouter } from "next/navigation";
import { ChangeEvent, useState } from "react";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

import styles from "./RegisterForm.module.css";
// import handler from '@/app/api/auth/register';
import RegisterHandler from '@/lib/register';

export const RegisterForm = () => {
  const router = useRouter();
  const [loading, setLoading] = useState(false);
  const [formValues, setFormValues] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    phoneNumber: "",
    income: "",
    street: "",
    state: "",
    city: "",
    zip: "",

  });
  const [error, setError] = useState("");

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      setLoading(true);
      setFormValues({ firstName: "", 
                      lastName: "", 
                      email: "", 
                      password: "", 
                      phoneNumber: "", 
                      income: "",
                      street: "", 
                      state: "",
                      city: "",
                      zip: "",
                    });

      const fakeFormData = {
          firstName: "james",
          lastName: "jimmybob",
          email: "1112@gmail.com",
          password: "password",
          phoneNumber: "1231232",
          income: 11115.00,
          address: {
            street: "12343 nutmeg ",
            state_state_id: 1,
            city: 'Tucson',
            zip_zip_id: 1
          }
      }

      const response = await RegisterHandler(fakeFormData)

      setLoading(false);

      if (response.ok) {
        // Registration successful, redirect to login page
        router.push("/auth/login");
      } else {
        // Handle registration error
        const data = await response.json();
        console.error(data.error);
        setError(data.error);
      }
    } catch (error: any) {
      setLoading(false);
      setError(error.message || "Something went wrong");
    }
  };

  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormValues({ ...formValues, [name]: value });
  };

  return (
    <Card className={styles.card}>
      <Card.Header className={styles.cardHeader}>Register</Card.Header>
      <form onSubmit={onSubmit} className={styles.form}>
        {error && <p className={styles.error}>{error}</p>}
        <div className={styles.inputDiv}>
          <input
            required
            type="text"
            name="firstName"
            value={formValues.firstName}
            onChange={handleChange}
            placeholder="First name"
            className={styles.input}
          />
        </div>
        <div className={styles.inputDiv}>
          <input
            required
            type="text"
            name="lastName"
            value={formValues.lastName}
            onChange={handleChange}
            placeholder="Last name"
            className={styles.input}
          />
        </div>
        <div className={styles.inputDiv}>
          <input
            required
            type="text"
            name="phoneNumber"
            value={formValues.phoneNumber}
            onChange={handleChange}
            placeholder="Phone Number"
            className={styles.input}
          />
        </div>

            {/* We're gonna make this into something NOT disgusting, but I'm trying to get functionality setup */}
        <div className={styles.inputDiv}> 
          <input
            required
            type="text"
            name="street"
            value={formValues.street}
            onChange={handleChange}
            placeholder="Street Address"
            className={styles.input}
          />
        </div>
        <div className={styles.inputDiv}>
          <input
            required
            type="text"
            name="state"
            value={formValues.state}
            onChange={handleChange}
            placeholder="State"
            className={styles.input}
          />
        </div>
        <div className={styles.inputDiv}> {/* we need to make this an object */}
          <input
            required
            type="text"
            name="city"
            value={formValues.city}
            onChange={handleChange}
            placeholder="City"
            className={styles.input}
          />
        </div>
        <div className={styles.inputDiv}> {/* we need to make this an object */}
          <input
            required
            type="text"
            name="zip"
            value={formValues.zip}
            onChange={handleChange}
            placeholder="Zipcode"
            className={styles.input}
          />
        </div>


        <div className={styles.inputDiv}>
          <input
            required
            type="text"
            name="income"
            value={formValues.income}
            onChange={handleChange}
            placeholder="Annual Income"
            className={styles.input}
          />
        </div>
        <div className={styles.inputDiv}>
          <input
            required
            type="email"
            name="email"
            value={formValues.email}
            onChange={handleChange}
            placeholder="Email address"
            className={styles.input}
          />
        </div>
        <div className={styles.inputDiv}>
          <input
            required
            type="password"
            name="password"
            value={formValues.password}
            onChange={handleChange}
            placeholder="Password"
            className={styles.input}
          />
        </div>
        <button
          type="submit"
          className={styles.button}
          disabled={loading}
        >
          {loading ? "Loading..." : "Register"}
        </button>
      </form>
      <Link href={'/auth/login'} className={styles.link}>
        Already have an account? Login!
      </Link>
    </Card>
  );
};
