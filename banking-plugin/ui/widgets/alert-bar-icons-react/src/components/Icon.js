import React from 'react';
import PropTypes from 'prop-types';
import { getSvg } from 'assets/svg/svg';

const Icon = ({ icon, className, style }) => {
  const imgSrc = `/entando-de-app/cmsresources/${getSvg(icon)}`;

  return <img id="my-icon" src={imgSrc} style={style} className={className} alt="icon" />;
};

Icon.propTypes = {
  icon: PropTypes.string.isRequired,
  className: PropTypes.string,
  style: PropTypes.shape({}),
};

Icon.defaultProps = {
  className: '',
  style: {},
};

export default Icon;
