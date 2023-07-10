/* eslint-disable @next/next/no-img-element */
"use client";

import Link from 'next/link';
import { signIn } from "next-auth/react";
import { useSearchParams, useRouter } from "next/navigation";
import { ChangeEvent, useState } from "react";

import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';


import styles from "./LoginForm.module.css"

export const LoginForm = () => {
  const router = useRouter();
  const [loading, setLoading] = useState(false);
  const [formValues, setFormValues] = useState({
    email: "",
    password: "",
  });
  const [error, setError] = useState("");

  const searchParams = useSearchParams();
  const callbackUrl = searchParams.get("callbackUrl") || "/mybank";

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      setLoading(true);
      setFormValues({ email: "", password: "" });

      const res = await signIn("credentials", {
        redirect: false,
        email: formValues.email,
        password: formValues.password,
        callbackUrl,
      });

      setLoading(false);

      console.log(res);
      if (!res?.error) {
        router.push(callbackUrl);
      } else {
        setError("invalid email or password");
      }
    } catch (error: any) {
      setLoading(false);
      setError(error);
    }
  };

  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormValues({ ...formValues, [name]: value });
  };

  return (
    <Card className={styles.card}>
      <Card.Header className={styles.cardHeader}>Login</Card.Header>
      <form onSubmit={onSubmit} className={styles.form}>
        {error && (
          <p className={styles.error}>{error}</p>
        )}
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
          // style={{ backgroundColor: `${loading ? "#ccc" : "#3446eb"}` }}
          className={styles.button}
          disabled={loading}
        >
          {loading ? "loading..." : "Sign In"}
        </button>
      </form>
      <Link href={'/auth/register'} className={styles.link}>
        No Account? Register!
      </Link>
    </Card>
  );
};
