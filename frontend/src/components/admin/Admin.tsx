'use client'
import Link from "next/link";
import { useSession } from "next-auth/react"
import { redirect } from 'next/navigation'
import { useState } from 'react';

import styles from './Admin.module.css'

const names = {
  loans: 'loans',
  cards: 'cards'
}

const Admin: React.FC<any> = (props:any) => {
  console.log("Admin Props >>> ", props.data);
  const data = props?.data;
  
  const [activeButton, setActiveButton] = useState('')

  const handlerSelector = (e:any) => {
    setActiveButton(e.target.name)
  }

  const { status } = useSession({
    required: true,
    onUnauthenticated() {
        redirect('/auth/login')
    },
  })

  return (
    <div>

      {data ? (
        <div>
          Something to show!
        </div>
      ) : (
        <div>Loading...</div>
      )}

    </div>
  )
}

export default Admin;