import { getServerSession } from "next-auth";
import Link from "next/link";

import Admin from "@/components/admin/Admin";

async function getData() {
  const data = [
      { //Loan data for Admin to approve/deny
      id: 1,
      balance: 1000.00, //the balance and originAmount should be the same at this point,
      original_amount: 1000.00, //as the user has just applied for this loan.
      interest_rate: 5.5,
      type : { //FK on the "loans" table for "loan_types"
        id: 1,
        type: 'Auto'
      },
      user: { //FK on the "loans" table for "users"
        id: 2,
        first_name: 'Bob',
        last_name: 'Builder',
        email: 'bb@gmail.com',
        phone: '0123456789',
        // role: { // we really shouldn't have to did this deep just to display the loans,

        // }
      }
    },
  ]
  return data;
}

export default async function Page() {
  const data = await getData();

  return (
    <main>
      <div>
        <Admin data={...data} />
      </div>
    </main>
  )
}