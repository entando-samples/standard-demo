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

export const apiUserGet = async (url, id) => {
  const fullUrl = `${url}/${resource}/${id}`;
  const options = {
    ...getDefaultOptions(),
    method: 'GET',
  };
  return request(fullUrl, options);
};

export const apiUserPost = async (url, user) => {
  const fullUrl = `${url}/${resource}`;
  const options = {
    ...getDefaultOptions(),
    method: 'POST',
    body: user ? JSON.stringify(user) : null,
  };
  return request(fullUrl, options);
};

export const apiUserPut = async (url, user) => {
  const fullUrl = `${url}/${resource}`;
  const options = {
    ...getDefaultOptions(),
    method: 'PUT',
    body: user ? JSON.stringify(user) : null,
  };
  return request(fullUrl, options);
};
