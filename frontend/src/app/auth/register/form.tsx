/* eslint-disable @next/next/no-img-element */
"use client";

import Link from 'next/link';
import { useSearchParams, useRouter } from "next/navigation";
import { ChangeEvent, useState } from "react";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

import styles from "./RegisterForm.module.css";
import handler from '@/app/api/auth/register';

export const RegisterForm = () => {
  const router = useRouter();
  const [loading, setLoading] = useState(false);
  const [formValues, setFormValues] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
  });
  const [error, setError] = useState("");

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      setLoading(true);
      setFormValues({ firstName: "", lastName: "", email: "", password: "" });

      const response = await handler(formValues)

      setLoading(false);

      if (response.ok) {
        // Registration successful, redirect to login page
        router.push("/auth/login");
      } else {
        // Handle registration error
        const data = await response.json();
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
