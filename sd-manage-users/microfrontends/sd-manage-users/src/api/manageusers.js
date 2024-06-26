import { getFilterQuery } from 'components/filters/utils';
import { getDefaultOptions, request, getUrl, getDefaultOptionsWithoutToken } from 'api/helpers';

const resource = 'manageusers';

export const apiManageusersDelete = async (serviceUrl, id) => {
  const url = `${serviceUrl}/api/${resource}/${id}`;
  const options = {
    ...getDefaultOptions(),
    method: 'DELETE',
  };
  return request(url, options);
};

export const apiManageusersGet = async (serviceUrl, { filters = [], pagination, mode }) => {
  const filterQuery = getFilterQuery(filters);
  const paginationQuery = pagination
    ? `page=${pagination.page}&size=${pagination.rowsPerPage}`
    : '';
  const url = getUrl(
    `${serviceUrl}/api/${resource}${mode === 'count' ? '/count' : ''}`,
    filterQuery,
    paginationQuery
  );
  const options = {
    ...getDefaultOptions(),
    method: 'GET',
  };

  return request(url, options);
};

export const apiAccountGet = async ({ serviceUrl, userid, account }) => {
  const url =
    account === 'savings'
      ? `${serviceUrl}/api/${account}?userID.equals=${userid}`
      : `${serviceUrl}/api/${account}s/user/${userid}`;

  const options = {
    ...getDefaultOptions(),
    method: 'GET',
  };
  return request(url, options);
};

export const apiAccountPost = async ({ serviceUrl, account, accountObj }) => {
  let url;
  if (account === 'checking') {
    url = `${serviceUrl}/add`;
  } else {
    url = `${serviceUrl}/api`;
  }

  url = account === 'savings' ? `${url}/${account}` : `${url}/${account}s`;

  const options = {
    ...(account === 'checking' ? getDefaultOptionsWithoutToken() : getDefaultOptions()),
    method: 'POST',
    body: accountObj ? JSON.stringify(accountObj) : null,
  };
  return request(url, options);
};

export const apiTransactionsGet = async ({ serviceUrl, accountid, account }) => {
  const url = `${serviceUrl}/api/${account}transactions?accountID.equals=${accountid}`;
  const options = {
    ...getDefaultOptions(),
    method: 'GET',
  };
  return request(url, options);
};

export const apiTransactionsPost = async ({ serviceUrl, account, transaction }) => {
  const url = `${serviceUrl}/api/${account}transactions`;

  const options = {
    ...getDefaultOptions(),
    method: 'POST',
    body: transaction ? JSON.stringify(transaction) : null,
  };
  return request(url, options);
};

export const apiNotificationsGet = async ({ serviceUrl, userid, notificationName }) => {
  const url = `${serviceUrl}/api/${notificationName}s?userId.equals=${userid}`;
  const options = {
    ...getDefaultOptions(),
    method: 'GET',
  };
  return request(url, options);
};

export const apiNotificationPost = async ({ serviceUrl, notificationName, notificationObj }) => {
  const url = `${serviceUrl}/api/${notificationName}s`;

  const options = {
    ...getDefaultOptions(),
    method: 'POST',
    body: notificationObj ? JSON.stringify(notificationObj) : null,
  };
  return request(url, options);
};

export const apiNotificationPut = async ({ serviceUrl, notificationName, notificationObj }) => {
  const url = `${serviceUrl}/api/${notificationName}s`;

  const options = {
    ...getDefaultOptions(),
    method: 'PUT',
    body: notificationObj ? JSON.stringify(notificationObj) : null,
  };
  return request(url, options);
};
