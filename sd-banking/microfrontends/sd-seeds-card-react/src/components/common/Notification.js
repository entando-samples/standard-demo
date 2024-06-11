/* eslint-disable react/jsx-props-no-spreading */
import React from 'react';
import PropTypes from 'prop-types';
import { Snackbar } from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';
import MuiAlert from '@material-ui/lab/Alert';

function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const styles = {};

const autoHideDurations = {
  success: 3000,
  error: null,
  info: 5000,
};

const Notification = ({ status: passedStatus, message, onClose }) => {
  const isOpen = !!message;

  // console.log(isOpen);
  // console.log(message);

  const status = passedStatus || Notification.INFO;
  const autoHideDuration = autoHideDurations[status];

  return (
    <Snackbar open={isOpen} autoHideDuration={autoHideDuration} onClose={onClose}>
      <Alert onClose={onClose} severity={status}>
        {message}
      </Alert>
    </Snackbar>
  );
};

Notification.SUCCESS = 'success';
Notification.ERROR = 'error';
Notification.INFO = 'info';

Notification.propTypes = {
  status: PropTypes.oneOf([Notification.SUCCESS, Notification.ERROR, Notification.INFO]),
  message: PropTypes.string,
  onClose: PropTypes.func,
};

Notification.defaultProps = {
  message: null,
  status: Notification.INFO,
  onClose: () => this.setState({ open: false }),
};

export default withStyles(styles, { withTheme: true })(Notification);
