import PropTypes from 'prop-types';

export default PropTypes.shape({
  id: PropTypes.number,

  firstname: PropTypes.string.isRequired,
  lastname: PropTypes.string.isRequired,
  email: PropTypes.string.isRequired,
  username: PropTypes.string.isRequired,
  password: PropTypes.string.isRequired,
  confirmPassword: PropTypes.string.isRequired,
});

export const formValues = PropTypes.shape({
  firstname: PropTypes.string.isRequired,
  lastname: PropTypes.string.isRequired,
  email: PropTypes.string,
  username: PropTypes.string,
  password: PropTypes.string,
  confirmPassword: PropTypes.string,
});

export const formTouched = PropTypes.shape({
  firstname: PropTypes.oneOfType([PropTypes.bool, PropTypes.shape()]),
  lastname: PropTypes.oneOfType([PropTypes.bool, PropTypes.shape()]),
  email: PropTypes.oneOfType([PropTypes.bool, PropTypes.shape()]),
  username: PropTypes.oneOfType([PropTypes.bool, PropTypes.shape()]),
  password: PropTypes.oneOfType([PropTypes.bool, PropTypes.shape()]),
  confirmPassword: PropTypes.oneOfType([PropTypes.bool, PropTypes.shape()]),
});

export const formErrors = PropTypes.shape({
  firstname: PropTypes.oneOfType([PropTypes.string, PropTypes.shape()]),
  lastname: PropTypes.oneOfType([PropTypes.string, PropTypes.shape()]),
  email: PropTypes.oneOfType([PropTypes.string, PropTypes.shape()]),
  username: PropTypes.oneOfType([PropTypes.string, PropTypes.shape()]),
  password: PropTypes.oneOfType([PropTypes.string, PropTypes.shape()]),
  confirmPassword: PropTypes.oneOfType([PropTypes.string, PropTypes.shape()]),
});
