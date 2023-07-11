import MyBankHome from '@/components/mybank/MyBankHome';
import YourAccounts from '@/components/yourAccounts/YourAccounts';
import { authOptions } from '@/lib/auth';
import {getServerSession} from 'next-auth/next'


//This is the new version of getServerSideProps
// https://nextjs.org/docs/app/building-your-application/data-fetching/fetching#async-and-await-in-server-components

async function getData() {
  try {
    const session = await getServerSession(authOptions);
    const userToken = await session?.user?.token;
    console.log(userToken);
    
    const res = await fetch(`${process.env.API_URL}/mybank/accounts`, {
      headers: {
        Authorization: 'Bearer ' + userToken,
        'Content-Type': 'application/json'
      },
    });

    if(res.ok) {
      const data = await res.json();
      return data;
    }
  } catch (e) {
    console.error('no account', e)
    return null;
  }
}

export default async function Page() {

  const data = await getData();

  return (
    <main>
     <MyBankHome {...data}/>
    </main>
  )
}