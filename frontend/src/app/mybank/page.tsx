import MyBankHome from '@/components/mybank/MyBankHome';


//This is the new version of getServerSideProps
// https://nextjs.org/docs/app/building-your-application/data-fetching/fetching#async-and-await-in-server-components
async function getData() {
  // const res = await fetch('OUR_API_CALL_URL');

  //FAKE DATA
  const data = {
    accountNumber:'1293912921394', 
    initialBalance:50, 
    accountType:'Checking', //Here up is accounts
    cardType: "Credit",
    cardNumber: "1234",
    initialCardBalance: 1106.67,
    creditLimit: 5000.00,
    expirationDate: "04/28" //Here up until the next comment is credit
  }

  return data;
}

export default async function Page() {
  const data = await getData();

  return (
    <main>
      <div>
        <MyBankHome {...data} />
      </div>
    </main>
  )
}

