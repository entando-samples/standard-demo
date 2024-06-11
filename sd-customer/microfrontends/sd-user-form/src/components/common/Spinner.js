import React from 'react';
import PropTypes from 'prop-types';
import { CircularProgress } from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';

const styles = () => ({
  message: {
    display: 'flex',
    alignItems: 'center',
    width: '100%',
    height: '100%',
    zIndex: '9999',
    backgroundColor: 'rgba(232, 232, 232, 0.4)',
    position: 'absolute',
    top: '0',
    left: '0',
  },
  icon: {
    margin: 'auto',
  },
});

const Spinner = ({ classes }) => {
  return (
    <span className={classes.message}>
      <CircularProgress className={classes.icon} />
    </span>
  );
};

Spinner.propTypes = {
  classes: PropTypes.shape({
    message: PropTypes.string.isRequired,
    icon: PropTypes.string.isRequired,
    iconStatus: PropTypes.string.isRequired,
    error: PropTypes.string.isRequired,
  }).isRequired,
};

Spinner.defaultProps = {};

export default withStyles(styles, { withTheme: true })(Spinner);
