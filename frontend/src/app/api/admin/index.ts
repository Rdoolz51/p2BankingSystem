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