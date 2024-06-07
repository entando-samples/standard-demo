import PropTypes from 'prop-types';

const seedscardType = PropTypes.shape({
  id: PropTypes.number,

  accountNumber: PropTypes.string,
  balance: PropTypes.number,
  userId: PropTypes.string,
});

export default seedscardType;
