import { DOMAIN } from 'api/constants';

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

const defaultOptions = () => {
  const token = getKeycloakToken();

  return {
    headers: new Headers({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    }),
  };
};

const executeFetch = (params = {}) => {
  const { url, options } = params;
  return fetch(url, {
    method: 'GET',
    ...defaultOptions(),
    ...options,
  })
    .then(response =>
      response.status >= 200 && response.status < 300
        ? Promise.resolve(response)
        : Promise.reject(new Error(response.statusText || response.status))
    )
    .then(response => response.json());
};

export const getSeedscard = (params = {}) => {
  const { id, options, cardname } = params;

  const url = `${DOMAIN}${DOMAIN.endsWith('/') ? '' : '/'}${cardname}/api/${cardname}s/${id}`;

  return executeFetch({ url, options });
};

export const getSeedscardByUserID = (params = {}) => {
  const { userID, options, cardname } = params;

  const url = `${DOMAIN}${
    DOMAIN.endsWith('/') ? '' : '/'
  }${cardname}/api/${cardname}s/user/${userID}`;

  return executeFetch({ url, options });
};
