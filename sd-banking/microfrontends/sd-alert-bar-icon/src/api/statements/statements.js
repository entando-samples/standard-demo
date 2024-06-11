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

const resource = 'statements';

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

export const apiStatementsGet = async (serviceUrl, { filters = [], pagination }) => {
  const filterQuery = getFilterQuery(filters);
  const paginationQuery = pagination
    ? `page=${pagination.page}&size=${pagination.rowsPerPage}`
    : '';
  const url = getUrl(`${serviceUrl}/api/${resource}`, filterQuery, paginationQuery);
  const options = {
    ...getDefaultOptions(),
    method: 'GET',
  };
  return request(url, options);
};

export const apiStatementPut = async (serviceUrl, statement) => {
  const url = `${serviceUrl}/${resource}`;
  const options = {
    ...getDefaultOptions(),
    method: 'PUT',
    body: statement ? JSON.stringify(statement) : null,
  };
  return request(url, options);
};
