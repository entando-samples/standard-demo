import React from 'react';
import PropTypes from 'prop-types';

const Title = ({ title, className, style }) => {
  return (
    <span id="my-title" className={className} style={style}>
      {title}
    </span>
  );
};

Title.propTypes = {
  title: PropTypes.string.isRequired,
  className: PropTypes.string,
  style: PropTypes.shape({}),
};

Title.defaultProps = {
  className: '',
  style: {},
};

export default Title;
