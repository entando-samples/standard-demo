import { DOMAIN } from 'api/constants';

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

export const apiSeedscardtransactionsAccountPost = async ({ checkingAccount }) => {
  const checking = 'checking';
  const baseUrl = DOMAIN.replace('signup/api', checking);

  const url = `${baseUrl}${baseUrl.endsWith('/') ? '' : '/'}add/${checking}s`;

  const options = {
    ...getDefaultOptions(),
    method: 'POST',
    body: checkingAccount ? JSON.stringify(checkingAccount) : null,
  };
  return request(url, options);
};

export const apiSeedsdashboardAlertPost = async ({ alertObj }) => {
  const seedsdashboard = 'seedsdashboard';
  const alert = 'alert';
  const baseUrl = DOMAIN.replace('signup', seedsdashboard);

  const url = `${baseUrl}${baseUrl.endsWith('/') ? '' : '/'}${alert}s`;

  const options = {
    ...getDefaultOptions(),
    method: 'POST',
    body: alertObj ? JSON.stringify(alertObj) : null,
  };
  return request(url, options);
};

export const apiSeedsdashboardStatementPost = async ({ statementObj }) => {
  const seedsdashboard = 'seedsdashboard';
  const statement = 'statement';
  const baseUrl = DOMAIN.replace('signup', seedsdashboard);

  const url = `${baseUrl}${baseUrl.endsWith('/') ? '' : '/'}${statement}s`;

  const options = {
    ...getDefaultOptions(),
    method: 'POST',
    body: statementObj ? JSON.stringify(statementObj) : null,
  };
  return request(url, options);
};
