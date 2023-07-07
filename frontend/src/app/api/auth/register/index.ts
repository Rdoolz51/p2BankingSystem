
export default async function handler(data: any) {

  try {
    console.log('REQUESt', data);
    const res = {
      ok: true,
    }
    return res;
  } catch (e) {
    console.error('Error in register.ts >>> ', e)
  }
}
