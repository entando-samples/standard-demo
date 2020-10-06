import React from 'react';
import PropTypes from 'prop-types';
import { withTranslation } from 'react-i18next';

import { Grid } from '@material-ui/core';
import { compose } from 'recompose';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';

const styles = theme => ({
  root: {
    margin: theme.spacing(0),
  },

  AddUserSuccess: {
    background: '#D8EAA0',
    width: '100%',
    height: '100vh',
    display: 'flex',
  },
  AddUserSuccess__title: {
    color: '#0E6837',
    textAlign: 'center',
    fontWeight: '600',
    fontSize: '2rem',
  },

  AddUserSuccess__redirect: {
    background: '#0E6837',
    fontSize: '1rem',
    textTransform: 'uppercase',
    color: '#fff',
    padding: '.84rem 2.14rem',
    width: '100%',
    borderRadius: '0',
    boxShadow: '0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12)',
    border: 'none',
    cursor: 'pointer',
    '& :hover': {
      background: '#0E6837',
      color: '#fff',
      padding: '.84rem 2.14rem',
      width: '100%',
      borderRadius: '0',
      boxShadow: '0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12)',
      border: 'none',
      cursor: 'pointer',
    },
  },

  AddUserSuccess__wrapper: {
    background: '#fff',
    padding: '1% 4% ',
    margin: '8% 31%',
    '@media (max-width: 1024px ) and (min-width: 320px) and (orientation: portrait )': {
      background: '#fff',
      padding: '0',
      margin: '8% 5%',
    },
    '@media (min-width: 320px) and (max-width: 1024px)  and (orientation: landscape )': {
      background: '#fff',
      padding: '0',
      margin: '8%',
    },
  },
  AddUserSuccess__button: {},
});

const AddUserSuccess = ({ onLogin, classes }) => {
  const handleClickLogin = () => {
    onLogin({ goToLogin: true });
  };

  return (
    <div className={classes.AddUserSuccess}>
      <Grid container className={classes.AddUserSuccess__wrapper}>
        <Grid item xs={12} className={classes.AddUserSuccess__title}>
          Congratulations, registration completed!
        </Grid>

        <Grid item xs={12} className={classes.AddUserSuccess__button}>
          <Typography className={classes.root}>
            {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
            <button
              type="button"
              className={classes.AddUserSuccess__redirect}
              onClick={handleClickLogin}
            >
              Click here to login
            </button>
          </Typography>
        </Grid>
      </Grid>
    </div>
  );
};

AddUserSuccess.propTypes = {
  classes: PropTypes.shape({
    root: PropTypes.string,
    textField: PropTypes.string,
    submitButton: PropTypes.string,
    button: PropTypes.string,
    AddUserSuccess: PropTypes.string,
    AddUserSuccess__title: PropTypes.string,
    AddUserSuccess__submit: PropTypes.string,
    AddUserSuccess__wrapper: PropTypes.string,
    AddUserSuccess__redirect: PropTypes.string,
    AddUserSuccess__button: PropTypes.string,
  }),
  onLogin: PropTypes.func.isRequired,
};

AddUserSuccess.defaultProps = {
  classes: {},
};

export default compose(
  withStyles(styles, { withTheme: true }),
  withTranslation()
)(AddUserSuccess);
