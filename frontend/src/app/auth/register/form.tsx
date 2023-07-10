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

import { states, zipCodes } from './content';

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
    address: {
      street: "",
      state: {
        id: ''
      },
      city: "",
      zip: {
        id: ''
      },
    }

  });
  const [error, setError] = useState("");

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      setLoading(true);
      setFormValues(prevState => ({ 
        ...prevState,
        firstName: "", 
        lastName: "", 
        email: "", 
        password: "", 
        phoneNumber: "", 
        income: "",
        address: {
          ...prevState.address,
          street: "", 
          state: {id: ''},
          city: "",
          zip: {id: ''},
        }
      }));
      
      const response = await RegisterHandler(formValues)

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

  const handleChange = (event: any) => {
    const { name, value } = event.target;
  
    // Create a copy of the address object
    const updatedAddress = { ...formValues.address };
  
    // Update the specific properties within the address object
    if (name.startsWith("address.street")) {
      updatedAddress.street = value;
    } else if (name.startsWith("address.state.id")) {
      updatedAddress.state.id = value;
    } else if (name.startsWith("address.city")) {
      updatedAddress.city = value;
    } else if (name.startsWith("address.zip.id")) {
      updatedAddress.zip.id = value;
    }
  
    // Update the form values with the updated address object
    setFormValues((prevState) => ({
      ...prevState,
      address: updatedAddress,
      [name]: value,
    }));
  };
  

  // const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
  //   const { name, value } = event.target;
  //   setFormValues({ ...formValues, [name]: value });
  // };

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
            name="address.street"
            value={formValues.address.street}
            onChange={handleChange}
            placeholder="Street Address"
            className={styles.input}
          />
        </div>
        <div className={styles.inputDiv}>
          <select
            required
            name="address.state.id"
            value={formValues.address.state.id}
            onChange={handleChange}
            className={styles.input}
          >
            <option value="">Select State</option>
            {/* Render options dynamically based on the states array */}
            {states.map((state) => (
              <option key={state.id} value={state.id}>
                {state.name}
              </option>
            ))}
          </select>

        </div>
        <div className={styles.inputDiv}>
          <input
            required
            type="text"
            name="address.city"
            value={formValues.address.city}
            onChange={handleChange}
            placeholder="City"
            className={styles.input}
          />
        </div>

        <div className={styles.inputDiv}>
        <select
          required
          name="address.zip.id"
          value={formValues.address.zip.id}
          onChange={handleChange}
          className={styles.input}
        >
          <option value="">Select Zip Code</option>
          {/* Render options dynamically based on the zipCodes array */}
          {zipCodes.map((zipCode) => (
            <option key={zipCode.id} value={zipCode.id}>
              {zipCode.zipCode}
            </option>
          ))}
        </select>


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
