import { DOMAIN } from 'api/constants';

const getDefaultOptions = () => ({
  headers: new Headers({
    'Content-Type': 'application/json',
  }),
});

const resource = 'users';

const request = async (url, options) => {
  const response = await fetch(url, options);

  return response.status >= 200 && response.status < 300
    ? response.json()
    : Promise.reject(new Error(response.status || response.statusText));
};

export const apiUserGet = async id => {
  const url = `${DOMAIN}/${resource}/${id}`;
  const options = {
    ...getDefaultOptions(),
    method: 'GET',
  };
  return request(url, options);
};

export const apiUserPost = async user => {
  const url = `${DOMAIN}/${resource}`;
  const options = {
    ...getDefaultOptions(),
    method: 'POST',
    body: user ? JSON.stringify(user) : null,
  };
  return request(url, options);
};

export const apiUserPut = async user => {
  const url = `${DOMAIN}/${resource}`;
  const options = {
    ...getDefaultOptions(),
    method: 'PUT',
    body: user ? JSON.stringify(user) : null,
  };
  return request(url, options);
};
