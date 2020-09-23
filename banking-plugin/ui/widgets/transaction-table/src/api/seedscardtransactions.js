import { DOMAIN } from 'api/constants';
import { getFilterQuery } from 'components/filters/utils';

const getKeycloakToken = () => {
  if (
    window &&
    window.entando &&
    window.entando.keycloak &&
    window.entando.keycloak.authenticated
  ) {
    return window.entando.keycloak.token;
  }
  return '';
};

const getDefaultOptions = () => ({
  headers: new Headers({
    Authorization: `Bearer ${getKeycloakToken()}`,
    'Content-Type': 'application/json',
  }),
});

const request = async (url, options) => {
  const response = await fetch(url, options);

  const headers = {
    ...(response.headers.has('X-Total-Count')
      ? { 'X-Total-Count': parseInt(response.headers.get('X-Total-Count'), 10) }
      : {}),
  };

  return response.status >= 200 && response.status < 300
    ? { seedscardtransactions: await response.json(), headers }
    : Promise.reject(new Error(response.statusText || response.status));
};

const getUrl = (url, filters = '', pagination = '') => {
  const hasQuery = !!(filters || pagination);
  const parameters = `${filters}${filters ? '&' : ''}${pagination}`;
  return `${url}${hasQuery ? `?${parameters}` : ''}`;
};

export const apiSeedscardtransactionsDelete = async id => {
  const url = `${DOMAIN}/seedscardtransactions/${id}`;
  const options = {
    ...getDefaultOptions(),
    method: 'DELETE',
  };
  return request(url, options);
};

export const apiSeedscardtransactionsGet = async ({ filters = [], pagination, mode, cardname }) => {
  const filterQuery = getFilterQuery(filters);

  const paginationQuery = pagination
    ? `page=${pagination.page}&size=${pagination.rowsPerPage}`
    : '';

  const param = cardname.replace(/\s+/g, '');

  const url = getUrl(
    `${DOMAIN}${DOMAIN.endsWith('/') ? '' : '/'}${param}transaction/api/${param}transactions${
      mode === 'count' ? '/count' : ''
    }`,
    filterQuery,
    paginationQuery
  );

  const options = {
    ...getDefaultOptions(),
    method: 'GET',
  };
  return request(url, options);
};
