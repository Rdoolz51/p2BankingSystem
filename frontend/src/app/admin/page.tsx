import { getServerSession } from "next-auth";
import { authOptions } from '@/lib/auth';
import { redirect } from 'next/navigation'
import Link from "next/link";

import Admin from "@/components/admin/Admin";
import { getPendingLoans, getPendingCreditCards } from "../api/admin";

async function getData() {
  const session = await getServerSession(authOptions);
  if(!session || session?.user?.role != 'Admin') {
    redirect('/auth/login')
  }
  console.log("admin session ", session);
  
  try {
    const userToken = session.user.token;

    const loanData = await getPendingLoans(userToken)
    const CCData = await getPendingCreditCards(userToken)
    return {loanData, CCData};


  } catch (e) {
    console.error('Something went wrong: ', e)
    return null;
  }
}

export default async function Page(props:any) {
  

  console.log("Passed props to admin/page >>> ", props);
  const data = await getData();

  console.log('data for admin >>> ', data);

  return (
    <main>
      <div>
        <Admin data={...data} />
      </div>
    </main>
  )
}