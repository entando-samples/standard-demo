if (process.env.NODE_ENV === 'production') {
  let publicpath = window.entando?.widgets['sd-manage-users']?.basePath;
  if (publicpath && publicpath.slice(-1) !== '/') {
    publicpath = `${publicpath}/`;
  }
  /* eslint-disable camelcase, no-undef */
  __webpack_public_path__ = publicpath || './';
}
