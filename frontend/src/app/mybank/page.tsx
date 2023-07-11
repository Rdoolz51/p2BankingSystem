import YourAccounts from '@/components/yourAccounts/YourAccounts';
const axios = require('axios').default


//This is the new version of getServerSideProps
// https://nextjs.org/docs/app/building-your-application/data-fetching/fetching#async-and-await-in-server-components
  async function getData() {
    try {
      const res = await fetch(`${process.env.API_URL}/mybank/accounts`, {
        headers: {
          Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZG9vbHo1MUBhb2wuY29tIiwiaWQiOjIyLCJyb2xlIjoiQ3VzdG9tZXIiLCJmaXJzdF9uYW1lIjoiUnlhbiIsImxhc3RfbmFtZSI6IkRvb2xleSIsImlhdCI6MTY4OTA3NDYyOCwiZXhwIjoxNjg5MDc1MjI4fQ.aVTzIF1cV3jQIyEp0MB_vehXcPGW3OZrgq5ql8yjW5NuX2w5GtYxnt3ckAIuk4QVliASLsIqbE9GeS4Sr9PUig",
          'Content-Type': 'application/json'
        }
        })
      if(res.ok) {
        const data = await res.json();
        return data;
      }
    } catch (e) {
      console.error('no account')
      return null;
    }
  }



export default async function Page() {
  const data = await getData();
  
  return (
    <main>
      <YourAccounts {...data} />
    </main>
  )
}