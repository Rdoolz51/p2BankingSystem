import YourCards from '@/components/yourCards/YourCards'

async function getData() {;
    //Using Fake data
  const data = {
    cardType: "Credit",
    cardNumber: "1234",
    initialCardBalance: 1106.67,
    creditLimit: 5000.00
  }

  return data;
}

export default async function Page() {
  const data = await getData();

  return (
    <main>
      <YourCards {...data} />
    </main>
  )
}