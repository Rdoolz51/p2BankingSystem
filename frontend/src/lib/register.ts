
export default async function RegisterHandler(data: any) {

  try {
    console.log('REQUESt', data);
    const res = await fetch(`http://localhost:8080/auth/register`, {
      method: 'POST',
      body: JSON.stringify(data),
      headers: { "Content-Type": "application/json" }
    })
    return res;
  } catch (e) {
    console.error('Error in register.ts >>> ', e)
  }
}
