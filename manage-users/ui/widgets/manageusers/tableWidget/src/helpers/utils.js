export const getRandomAccountNumber = () => {
  let accountNumber = '';
  while (accountNumber.length !== 12) {
    accountNumber = `${Math.floor(100000 + Math.random() * 9000000000000)}`;
  }
  return accountNumber;
};

export const getRandomElement = list => {
  return list[Math.floor(Math.random() * list.length)];
};

export const getDate = counter => {
  const d = new Date();
  d.setDate(d.getDate() - counter);
  return d.toISOString().substring(0, 10);
};

const getUpdatedBalance = (amount, balance) => {
  return balance - amount;
};

export const getTransactionsList = ({ b, accountID }) => {
  let counter = 1;
  let balance = b;

  const description1 = 'Cake mix expense';
  const description2 = 'Chocolate covered crickets';
  const description3 = 'antenna tower';
  const description4 = 'Greetings from the galaxy';
  const description5 = 'toasted cheese and tuna sandwiches';
  const description6 = 'research paper on Friday';
  const description7 = 'road work ahead';
  const description8 = 'Interest expense';
  const description9 = 'Telephone expense';
  const description10 = 'Repairs';
  const description11 = 'Oven';
  const description12 = 'Computer';
  const description13 = 'Car ensurance';
  const description14 = 'First transaction';
  const description15 = 'Second transaction';
  const description16 = 'Third transaction';
  const descriptionList = [
    description1,
    description2,
    description3,
    description4,
    description5,
    description6,
    description7,
    description8,
    description9,
    description10,
    description11,
    description12,
    description13,
    description14,
    description15,
    description16,
  ];

  const amount1 = '3.50';
  const amount2 = '23.72';
  const amount3 = '413.95';
  const amount4 = '58.50';
  const amount5 = '24.39';
  const amount6 = '60.63';
  const amount7 = '48.84';
  const amount8 = '28.79';
  const amount9 = '49.98';
  const amount10 = '94.77';
  const amount11 = '71.79';
  const amount12 = '37.75';
  const amount13 = '9.25';
  const amount14 = '19.17';
  const amount15 = '32.98';
  const amount16 = '41.38';
  const amountList = [
    amount1,
    amount2,
    amount3,
    amount4,
    amount5,
    amount6,
    amount7,
    amount8,
    amount9,
    amount10,
    amount11,
    amount12,
    amount13,
    amount14,
    amount15,
    amount16,
  ];
  const transactionsList = [];
  // eslint-disable-next-line no-plusplus
  for (let i = counter; i < 17; i++) {
    const amount = getRandomElement(amountList);
    if (i > 1) {
      balance = getUpdatedBalance(amount, balance);
    }
    const transaction = {
      date: getDate(counter),
      description: getRandomElement(descriptionList),
      amount,
      balance,
      accountID,
    };
    transactionsList.push(transaction);
    // eslint-disable-next-line no-plusplus
    counter++;
  }
  return transactionsList;
};

export const buildObj = ({ description, id }) => {
  return {
    description,
    createdAt: new Date().toISOString().substring(0, 10),
    read: false,
    userId: id,
  };
};

export const getNotificationList = ({ userId }) => {
  const description1 = 'Welcome in your bank';
  const description2 = 'Checking account created';
  const description3 = 'Internet banking ACTIVE';
  const description4 = 'Info banking';
  const notificationList = [
    buildObj({ description: description1, id: userId }),
    buildObj({ description: description2, id: userId }),
    buildObj({ description: description3, id: userId }),
    buildObj({ description: description4, id: userId }),
  ];
  return notificationList;
};
