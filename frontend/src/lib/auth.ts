import type { NextAuthOptions } from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";
var jwt = require('jsonwebtoken');
import { Session } from 'next-auth';


export const authOptions: NextAuthOptions = {
  pages: {
    signIn: '/auth/login'
  },
  session: {
    strategy: "jwt",
  },
  providers: [
    CredentialsProvider({
      name: "Sign in",
      credentials: {
        email: { label: "Email", type: "email", placeholder: "example@example.com", },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials) {

        const res = await fetch(`${process.env.API_URL}/auth/login`, {
          method: 'POST',
          body: JSON.stringify(credentials),
          headers: { "Content-Type": "application/json" }
        })
        const userRes = await res.json();
        const tokenTest = await userRes.token
        function parseJwt(token: string) {
          try {
            const decodedToken = jwt.verify(token, process.env.JWT_SECRET);
            return decodedToken;
          } catch (error) {
            console.error('Error decoding JWT token:', error);
            return null;
          }
        }
        
        const userData = parseJwt(userRes.token)
        const user = {
          id: userData.id,
          email: userData.sub,
          firstName: userData.first_name,
          lastName: userData.last_name,
          role: userData.role,
          token: tokenTest,
        }
        
        // If no error and we have user data, return it
        if (res.ok && user) {
          console.log('RETURNED USER >>> ',  user);
          return user
        }
        // Return null if user data could not be retrieved
        return null
      },
    }),
  ],
  callbacks: {
    async jwt({token, user}) {
      
      if (user?.id) {
          token.id = user.id
      }
      if (user?.email) {
          token.email = user.email;
      }
      if (user?.firstName) {
        token.firstName = user.firstName;
      }
      if(user?.lastName) {
        token.lastName = user.lastName;
      }
      if (user?.role) {
        token.role = user.role;
      }
      if(user?.token) {
        token.token = user.token;
      }
      return token
   },
   async session({session, token}) {
      if(session.user) {
        delete session.user.name // NextAuth includes this automatically, we don't want it
        session.user.id = token.id;
        session.user.email = token.email;
        session.user.firstName = token.firstName;
        session.user.lastName = token.lastName;
        session.user.role = token.role;
        session.user.token = token.token;
      }
      return session;
   }
  }
};
