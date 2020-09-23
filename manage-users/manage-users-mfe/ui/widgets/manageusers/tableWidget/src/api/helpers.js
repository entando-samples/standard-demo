export const getKeycloakToken = () => {
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

export const getDefaultOptions = () => ({
  headers: new Headers({
    Authorization: `Bearer ${getKeycloakToken()}`,
    'Content-Type': 'application/json',
  }),
});

export const getDefaultOptionsWithoutToken = () => ({
  headers: new Headers({
    'Content-Type': 'application/json',
  }),
});

export const getUrl = (url, filters = '', pagination = '') => {
  const hasQuery = !!(filters || pagination);
  const parameters = `${filters}${filters ? '&' : ''}${pagination}`;
  return `${url}${hasQuery ? `?${parameters}` : ''}`;
};

export const request = async (url, options) => {
  const response = await fetch(url, options);

  // const headers = {
  //   ...(response.headers.has('X-Total-Count')
  //     ? { 'X-Total-Count': parseInt(response.headers.get('X-Total-Count'), 10) }
  //     : {}),
  // };

  if (response.status === 204) {
    return '';
  }

  if (response.status === 404) {
    return null;
  }

  return response.status >= 200 && response.status < 300
    ? // eslint-disable-next-line no-return-await
      await response.json()
    : Promise.reject(new Error(response.statusText || response.status));
};
