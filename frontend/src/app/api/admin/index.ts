export const getPendingLoans = async (token:String) => {
  try {
    const res = await fetch(`${process.env.API_URL}/admin/loans-pending`, {
      headers: {
        Authorization: 'Bearer ' + token,
        'Content-Type': 'application/json'
      },
    });

    if(res.ok) {
      const data = await res.json();
      return data;
    } else {
      console.error('Something went wrong (/admin) ')
      return null;
    }
  } catch (e) {
    console.error('Something went wrong getting admin stuff: ', e)
    return null;
  }
}

export const getPendingCreditCards = async (token:String) => {
  try {
    const res = await fetch(`${process.env.API_URL}/admin/cc-pending`, {
      headers: {
        Authorization: 'Bearer ' + token,
        'Content-Type': 'application/json'
      },
    });

    if(res.ok) {
      const data = await res.json();
      return data;
    } else {
      console.error('Something went wrong (/admin) ')
      return null;
    }
  } catch (e) {
    console.error('Something went wrong getting admin stuff: ', e)
    return null;
  }
}

export const approveOrDenyLoan = async (token:String, data:any) => {
  try {
    const res = await fetch(`${process.env.API_URL}/admin/loans`, {
      method: 'PUT',
      headers: {
        Authorization: 'Bearer ' + token,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data),
    });

    if(res.ok) {
      const data = await res.json();
      return data;
    } else {
      console.error('Something went wrong (/loans) ')
      return null;
    }
  } catch (e) {
    console.error('Something went wrong doing loan stuff: ', e)
    return null;
  }
}

export const approveOrDenyCC = async (token:String, data:any) => {
  try {
    const res = await fetch(`${process.env.API_URL}/admin/cards`, {
      method: 'PUT',
      headers: {
        Authorization: 'Bearer ' + token,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data),
    });

    if(res.ok) {
      const data = await res.json();
      return data;
    } else {
      console.error('Something went wrong (/cards) ')
      return null;
    }
  } catch (e) {
    console.error('Something went wrong doing card stuff: ', e)
    return null;
  }
}