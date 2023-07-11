import AtmPinPad from "@/components/atm/atmPinPad/AtmPinPad";


//This is the new version of getServerSideProps
// https://nextjs.org/docs/app/building-your-application/data-fetching/fetching#async-and-await-in-server-components
async function getData() {
  // const res = await fetch('OUR_API_CALL_URL');

  //FAKE DATA
  const data = {
  }

  return data;
}

export default async function Page() {
  const data = await getData();

  return (
    <main>
      <div>
        <AtmPinPad />
      </div>
    </main>
  )
}

