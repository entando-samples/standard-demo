const getDefaultOptions = () => ({
  headers: new Headers({
    'Content-Type': 'application/json',
  }),
});

const request = async (url, options) => {
  const response = await fetch(url, options);

  return response.status >= 200 && response.status < 300
    ? response.json()
    : Promise.reject(new Error(response.status || response.statusText));
};

export const apiSeedscardtransactionsAccountPost = async ({ bankingUrl, checkingAccount }) => {
  const url = `${bankingUrl}/add/checkings`;

  const options = {
    ...getDefaultOptions(),
    method: 'POST',
    body: checkingAccount ? JSON.stringify(checkingAccount) : null,
  };
  return request(url, options);
};

export const apiSeedsdashboardAlertPost = async ({ bankingUrl, alertObj }) => {
  const url = `${bankingUrl}/api/alerts`;

  const options = {
    ...getDefaultOptions(),
    method: 'POST',
    body: alertObj ? JSON.stringify(alertObj) : null,
  };
  return request(url, options);
};

export const apiSeedsdashboardStatementPost = async ({ bankingUrl, statementObj }) => {
  const url = `${bankingUrl}/api/statements`;

  const options = {
    ...getDefaultOptions(),
    method: 'POST',
    body: statementObj ? JSON.stringify(statementObj) : null,
  };
  return request(url, options);
};
