import PropTypes from 'prop-types';

const seedscardtransactionType = PropTypes.shape({
  id: PropTypes.number,

  date: PropTypes.string,
  description: PropTypes.string,
  amount: PropTypes.number,
  balance: PropTypes.number,
  accountID: PropTypes.number,
});

export default seedscardtransactionType;
