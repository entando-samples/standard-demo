import React from 'react';
import PropTypes from 'prop-types';
import { getSvg } from 'assets/svg/svg';

const Icon = ({ icon, className, style }) => {
  //TODO: rework this for any application path and/or upgrade to handling of static resources in the blueprint
  const imgPath = (process.env.NODE_ENV === 'development') ? '../' : '/entando-de-app/cmsresources/';
  const imgSrc = `${imgPath}${getSvg(icon)}`;

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
