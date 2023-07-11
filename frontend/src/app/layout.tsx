import "bootstrap/dist/css/bootstrap.min.css";
import './globals.css'
import { NextAuthProvider } from "./providers";
import Navbar from '@/components/navbar/Navbar';
import { authOptions } from "@/lib/auth";
import { getServerSession } from "next-auth/next";


export const metadata = {
  title: "Pursue Banking",
  description: "The Better Chase",
};

async function getData() {
  try {
    const session = await getServerSession(authOptions);
    const userToken = session?.user.token;
    if(session == null || session == undefined) {
        return null;
    }
    const res = await fetch(`${process.env.API_URL}/mybank`, {
      headers: {
        Authorization: 'Bearer ' + userToken,
        'Content-Type': 'application/json'
      }
    })
    if(res.ok) {
      const data = await res.json();
      return data;
    }
  } catch (e) {
    console.error('no bank', e)
    return null;
  }
}

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