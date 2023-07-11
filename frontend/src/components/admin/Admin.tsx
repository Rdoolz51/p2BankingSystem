import Link from "next/link";
import Image from "next/image";

import styles from './Admin.module.css'

const Admin: React.FC<any> = (props:any) => {
  console.log(props);
  const data = props?.data;

  return (
    <div>

      {data ? (
        <div>
          Something to show!
        </div>
      ) : (
        <div>Nothing to show!</div>
      )}

    </div>
  )
}

export default Admin;