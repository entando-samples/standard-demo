import React from 'react';
import PropTypes from 'prop-types';

const Counter = ({ count, className, style }) => {
  return (
    <span id="my-counter" className={className} style={style}>
      {count}
    </span>
  );
};

Counter.propTypes = {
  count: PropTypes.number.isRequired,
  className: PropTypes.string,
  style: PropTypes.shape({}),
};

Counter.defaultProps = {
  className: '',
  style: {},
};

export default Counter;
