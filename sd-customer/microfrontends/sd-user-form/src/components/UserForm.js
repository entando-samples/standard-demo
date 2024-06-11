import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
import { formValues, formTouched, formErrors } from 'components/__types__/user';
import { withFormik } from 'formik';
import { withTranslation } from 'react-i18next';
import { withStyles } from '@material-ui/core/styles';
import { compose } from 'recompose';
import * as Yup from 'yup';
import { Grid, TextField, InputAdornment, IconButton, Divider } from '@material-ui/core';
import { Visibility, VisibilityOff } from '@material-ui/icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { faFacebook, faLinkedin, faGithub, faTwitter } from '@fortawesome/free-brands-svg-icons';

const styles = theme => ({
  root: {
    margin: theme.spacing(0),
  },
  UserForm: {
    background: '#D8EAA0',
    margin: '-0.5rem',
  },
  UserForm__title: {
    color: '#0E6837',
    textAlign: 'center',
    fontWeight: '600',
    fontSize: '2rem',
  },
  UserForm__social: {
    textAlign: 'center',
    fontSize: '1rem',
    marginBottom: '1.375rem',
    color: '#000',
    '& a': {
      color: '#0E6837',
      fontSize: '0.625rem',
      margin: '0 .625rem',
    },
    '@media (max-width: 1024px ) and (min-width: 320px) and (orientation: portrait )': {
      fontSize: '1.5rem',
    },
    '@media (min-width: 320px) and (max-width: 1024px)  and (orientation: landscape )': {
      fontSize: '1.5rem',
    },
  },
  UserForm__login: {
    backgroung: '#184437',
  },
  UserForm__signup: {
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
  UserForm__row: {
    width: '100%',
  },
  UserForm__wrapper: {
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
  UserForm__textField: {
    width: '100%',
    background: '#fff',

    '& label': {
      fontSize: '1rem',
    },
  },
  input: {
    fontSize: '3rem!Important',
  },
  UserForm__agree: {
    textAlign: 'center',
    fontSize: '1rem',
    marginBottom: '1.375rem',
    color: '#000',
    '& a': {
      color: '#0E6837',
    },
    '@media (max-width: 1024px ) and (min-width: 320px) and (orientation: portrait )': {
      fontSize: '1.2rem',
    },
    '@media (min-width: 320px) and (max-width: 1024px)  and (orientation: landscape )': {
      fontSize: '1.2rem',
    },
  },
  UserForm__underlabel: {
    textAlign: 'center',
    fontSize: '0.875rem',
    marginTop: '-1.375rem',
    color: '#6c757d',
    '@media (max-width: 1024px ) and (min-width: 320px) and (orientation: portrait )': {
      fontSize: '1.1rem',
      marginTop: '-.375rem',
    },
    '@media (min-width: 320px) and (max-width: 1024px)  and (orientation: landscape )': {
      fontSize: '1.2rem',
    },
  },
  UserForm__divider: {
    margin: '1.25rem',
    padding: '0',
  },

  UserForm__input: {
    fontSize: '1rem',
    '@media (max-width: 1024px ) and (min-width: 320px) and (orientation: portrait )': {
      fontSize: '1.5rem',
    },
    '@media (min-width: 320px) and (max-width: 1024px)  and (orientation: landscape )': {
      fontSize: '1.5rem',
    },
  },
});
class UserForm extends PureComponent {
  render() {
    const {
      classes,
      values,
      touched,
      errors,
      handleChange,
      handleBlur,
      handleSubmit: formikHandleSubmit,
      isSubmitting,
      t,
      showPassword,
      handleClickShowPassword,
      handleMouseDownPassword,
    } = this.props;
    const getHelperText = field => (errors[field] && touched[field] ? errors[field] : '');
    const handleSubmit = e => {
      e.stopPropagation(); // avoids double submission caused by react-shadow-dom-retarget-events
      formikHandleSubmit(e);
    };
    return (
      <form onSubmit={handleSubmit} className={classes.UserForm} data-testid="user-form">
        <Grid container justify="center" alignItems="center">
          <Grid container spacing={4} className={classes.UserForm__wrapper}>
            <Grid item xs={12} sm={12} className={classes.UserForm__title}>
              Registration
            </Grid>
            <Grid item xs={12} md={6} className={classes.UserForm__row}>
              <TextField
                id="user-firstname"
                error={errors.firstname && touched.firstname}
                helperText={getHelperText('firstname')}
                className={classes.UserForm__textField}
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.firstname}
                name="firstname"
                label={t('entities.user.firstname')}
                type="firstname"
                InputProps={{
                  className: classes.UserForm__input,
                }}
              />
            </Grid>
            <Grid item xs={12} md={6} className={classes.UserForm__row}>
              <TextField
                id="user-lastname"
                error={errors.lastname && touched.lastname}
                helperText={getHelperText('lastname')}
                className={classes.UserForm__textField}
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.lastname}
                name="lastname"
                label={t('entities.user.lastname')}
                type="lastname"
                InputProps={{
                  className: classes.UserForm__input,
                }}
              />
            </Grid>
            <Grid item xs={12} md={12} className={classes.UserForm__row}>
              <TextField
                id="user-email"
                error={errors.email && touched.email}
                helperText={getHelperText('email')}
                className={classes.UserForm__textField}
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.email}
                name="email"
                label={t('entities.user.email')}
                type="email"
                InputProps={{
                  className: classes.UserForm__input,
                }}
              />
            </Grid>
            <Grid item xs={12} md={12} className={classes.UserForm__row}>
              <TextField
                id="user-username"
                error={errors.username && touched.username}
                helperText={getHelperText('username')}
                className={classes.UserForm__textField}
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.username}
                name="username"
                label={t('entities.user.username')}
                InputProps={{
                  className: classes.UserForm__input,
                }}
              />
            </Grid>
            <Grid item xs={12} md={12} className={classes.UserForm__row}>
              <TextField
                id="user-password"
                error={errors.password && touched.password}
                helperText={getHelperText('password')}
                className={classes.UserForm__textField}
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.password}
                name="password"
                label={t('entities.user.password')}
                type={showPassword ? 'text' : 'password'}
                InputProps={{
                  className: classes.UserForm__input,
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton
                        aria-label="toggle password visibility"
                        onClick={handleClickShowPassword}
                        onMouseDown={handleMouseDownPassword}
                      >
                        {showPassword ? <Visibility /> : <VisibilityOff />}
                      </IconButton>
                    </InputAdornment>
                  ),
                }}
              />
            </Grid>
            <Grid item xs={12} md={12} className={classes.UserForm__row}>
              <TextField
                id="confirm-user-password"
                error={errors.confirmPassword && touched.confirmPassword}
                helperText={getHelperText('confirmPassword')}
                className={classes.UserForm__textField}
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.confirmPassword}
                name="confirmPassword"
                label={t('entities.user.confirmPassword')}
                type="password"
                InputProps={{
                  className: classes.UserForm__input,
                }}
              />
            </Grid>
            <Grid item xs={12} className={classes.UserForm__underlabel}>
              At least 8 characters and 1 digit
            </Grid>

            <Grid item xs={12} md={12}>
              <button
                type="submit"
                disabled={isSubmitting}
                data-testid="submit-btn"
                className={classes.UserForm__signup}
              >
                Sign up
              </button>
            </Grid>

            <Grid item xs={12} md={12} className={classes.UserForm__social}>
              or sign up with:
            </Grid>
            <Grid container spacing={0} className={classes.UserForm__social}>
              <Grid item xs>
                <a href="www.test.it" className="UserForm__icons" role="button">
                  <FontAwesomeIcon
                    icon={faFacebook}
                    style={{ color: '#0E6837', height: '30px', width: '30px' }}
                  />
                </a>
                <a href="www.test.it" className="UserForm__icons" role="button">
                  <FontAwesomeIcon
                    icon={faTwitter}
                    style={{ color: '#0E6837', height: '30px', width: '30px' }}
                  />
                </a>
                <a href="www.test.it" className="UserForm__icons" role="button">
                  <FontAwesomeIcon
                    icon={faLinkedin}
                    style={{ color: '#0E6837', height: '30px', width: '30px' }}
                  />
                </a>
                <a href="www.test.it" className="UserForm__icons" role="button">
                  <FontAwesomeIcon
                    icon={faGithub}
                    style={{ color: '#0E6837', height: '30px', width: '30px' }}
                  />
                </a>
              </Grid>
              {/* Remove? spacing={3} */}
              <Grid item xs={12} md={12} className={classes.UserForm__divider}>
                <Divider />
              </Grid>
            </Grid>
            <Grid item xs={12} md={12} className={classes.UserForm__agree}>
              <p>
                By clicking <em>Sign up </em> you agree to our{' '}
                <a href="www.test.it" target="_blank">
                  {' '}
                  terms of service
                </a>
              </p>
            </Grid>
          </Grid>
        </Grid>
      </form>
    );
  }
}
UserForm.propTypes = {
  classes: PropTypes.shape({
    root: PropTypes.string,
    textField: PropTypes.string,
    submitButton: PropTypes.string,
    button: PropTypes.string,
    downloadAnchor: PropTypes.string,
    UserForm: PropTypes.string,
    UserForm__title: PropTypes.string,
    UserForm__submit: PropTypes.string,
    UserForm__row: PropTypes.string,
    UserForm__signin: PropTypes.string,
    UserForm__login: PropTypes.string,
    UserForm__agree: PropTypes.string,
    UserForm__textfield: PropTypes.string,
    UserForm__social: PropTypes.string,
    UserForm__underlabel: PropTypes.string,
    UserForm__textField: PropTypes.string,
    UserForm__wrapper: PropTypes.string,
    UserForm__signup: PropTypes.string,
    UserForm__icons: PropTypes.string,
    UserForm__input: PropTypes.string,
    UserForm__divider: PropTypes.string,
  }),
  values: formValues,
  touched: formTouched,
  errors: formErrors,
  handleChange: PropTypes.func.isRequired,
  handleBlur: PropTypes.func.isRequired,
  handleSubmit: PropTypes.func.isRequired,
  isSubmitting: PropTypes.bool.isRequired,
  // eslint-disable-next-line react/no-unused-prop-types
  setFieldValue: PropTypes.func.isRequired,
  t: PropTypes.func.isRequired,
  i18n: PropTypes.shape({ language: PropTypes.string }).isRequired,
  showPassword: PropTypes.bool.isRequired,
  handleClickShowPassword: PropTypes.func.isRequired,
  handleMouseDownPassword: PropTypes.func.isRequired,
};
UserForm.defaultProps = {
  classes: {},
  values: {},
  touched: {},
  errors: {},
};
const emptyUser = {
  firstname: '',
  lastname: '',
  email: '',
  username: '',
  password: '',
  confirmPassword: '',
};
// const emptyUser = {
//   firstname: 'user2',
//   lastname: 'user2',
//   email: 'user2@user2.user2',
//   username: 'user2',
//   password: 'user2user2',
//   confirmPassword: 'user2user2',
// };

const validationSchema = Yup.object().shape({
  firstname: Yup.string().required(),
  lastname: Yup.string().required(),
  email: Yup.string().required(),
  username: Yup.string().required(),
  password: Yup.string()
    .required()
    .min(8)
    .max(16),
  confirmPassword: Yup.string()
    .required()
    .when('password', {
      is: val => !!(val && val.length > 0),
      then: Yup.string().oneOf([Yup.ref('password')], 'Both password need to be the same'),
    }),
});
const formikBag = {
  mapPropsToValues: ({ user }) => user || emptyUser,
  enableReinitialize: true,
  validationSchema,
  handleSubmit: (values, { setSubmitting, props: { onSubmit } }) => {
    onSubmit(values);
    setSubmitting(false);
  },
  displayName: 'UserForm',
};
export default compose(
  withStyles(styles, { withTheme: true }),
  withTranslation(),
  withFormik(formikBag)
)(UserForm);
