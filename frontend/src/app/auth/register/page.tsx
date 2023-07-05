import { authOptions } from "@/lib/auth";
import { getServerSession } from "next-auth";


export default async function Register() {
  const session = await getServerSession(authOptions);
  console.log("register Session >>> ", session);

  return (
    <main>

      <div>
        Register Page
      </div>

    </main>
  )
}