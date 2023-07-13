'use client'
import { useState } from 'react';
import CardApp from "@/components/cardApp/CardApp";
import LoanApp from "@/components/loanApp/LoanApp";

export default function Page() {
  const [app, setApp] = useState<number | null>(null);

  const handleApp = (num:number) => {
    setApp(num);
  }

  return (
    <main>
      {app === 1 && <CardApp />}
      {app === 2 && <LoanApp />}
      
      <button onClick={() => handleApp(1)}>
        Show CardApp
      </button>
      <button onClick={() => handleApp(2)}>
        Show LoanApp
      </button>
    </main>
  );
}