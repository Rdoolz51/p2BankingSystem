import "bootstrap/dist/css/bootstrap.min.css";
import './globals.css'
import { NextAuthProvider } from "./providers";
import Navbar from '@/components/navbar/Navbar';

async function getData(){
  const data = {
    firstName: 'Ryan',
  lastName: 'Dooley',
  email: 'Rdoolz51@aol.com',
  phoneNumber: '570-951-2581',
  yearlyIncome: 65000
  }
  return data;
}

export const metadata = {
  title: "Pursue Banking",
  description: "The Better Chase",
};

export default async function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const data = await getData();
  return (
    <html lang="en">
      <body>
        <NextAuthProvider>
          <Navbar {...data} />
          {children}
        </NextAuthProvider>
      </body>
    </html>
  );
}