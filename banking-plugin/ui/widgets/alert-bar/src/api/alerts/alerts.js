import { DOMAIN } from 'api/constants';

const getDefaultOptions = () => ({
  headers: new Headers({
    // Authorization: `Bearer ${getKeycloakToken()}`,
    'Content-Type': 'application/json',
  }),
});

const resource = 'alerts';

const request = async (url, options) => {
  const response = await fetch(url, options);
  const headers = {
    ...(response.headers.has('X-Total-Count')
      ? { 'X-Total-Count': parseInt(response.headers.get('X-Total-Count'), 10) }
      : {}),
  };

  return response.status >= 200 && response.status < 300
    ? { data: await response.json(), headers }
    : Promise.reject(new Error(response.statusText || response.status));
};

export const getFilterQuery = (filters = []) => {
  if (filters.length) {
    return filters
      .filter(f => f.field && f.operator)
      .reduce((acc, f) => {
        switch (f.operator) {
          case 'specified':
            return [...acc, `${encodeURIComponent(`${f.field}.specified`)}=true`];
          case 'unspecified':
            return [...acc, `${encodeURIComponent(`${f.field}.specified`)}=false`];
          default:
        }
        return [
          ...acc,
          `${encodeURIComponent(`${f.field}.${f.operator}`)}=${encodeURIComponent(f.value)}`,
        ];
      }, [])
      .join('&');
  }
  return '';
};

const getUrl = (url, filters = '', pagination = '') => {
  const hasQuery = !!(filters || pagination);
  const parameters = `${filters}${filters ? '&' : ''}${pagination}`;
  return `${url}${hasQuery ? `?${parameters}` : ''}`;
};

export const apiAlertsGet = async ({ filters = [], pagination }) => {
  // const { userId } = params;
  const filterQuery = getFilterQuery(filters);
  const paginationQuery = pagination
    ? `page=${pagination.page}&size=${pagination.rowsPerPage}`
    : '';
  const url = getUrl(`${DOMAIN}/${resource}`, filterQuery, paginationQuery);
  const options = {
    ...getDefaultOptions(),
    method: 'GET',
  };
  return request(url, options);
};

export const apiAlertsPut = async statement => {
  const url = `${DOMAIN}/${resource}`;
  const options = {
    ...getDefaultOptions(),
    method: 'PUT',
    body: statement ? JSON.stringify(statement) : null,
  };
  return request(url, options);
};
