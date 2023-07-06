import YourAccounts from '@/components/yourAccounts/YourAccounts';


//This is the new version of getServerSideProps
// https://nextjs.org/docs/app/building-your-application/data-fetching/fetching#async-and-await-in-server-components
async function getData() {
  // const res = await fetch('OUR_API_CALL_URL');

  //FAKE DATA
  const data = {
    accountNumber:'1293912921394', 
    initialBalance:50, 
    accountType:'Checking',
  }

  return data;
}

export default async function Page() {
  const data = await getData();

  return (
    <main>
      <YourAccounts {...data} />
    </main>
  )
}