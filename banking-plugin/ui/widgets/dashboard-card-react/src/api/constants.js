// TODO: Standardize this better across widgets in the Standard Demo
// eslint-disable-next-line import/prefer-default-export
export const DOMAIN =
  process.env.NODE_ENV === 'development'
    ? process.env.REACT_APP_SERVICE_URL
    : process.env.REACT_APP_DOMAIN;
