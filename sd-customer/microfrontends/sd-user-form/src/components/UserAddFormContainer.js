import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
import { withTranslation } from 'react-i18next';
import { BANKING_API, CUSTOMER_API } from 'api/constants';

import { withKeycloak } from 'auth/KeycloakContext';
import { apiUserPost } from 'api/users';
import Notification from 'components/common/Notification';
import UserForm from 'components/UserForm';
import AddUserSuccess from 'components/AddUserSuccess';
import Spinner from 'components/common/Spinner';
import {
  apiSeedscardtransactionsAccountPost,
  apiSeedsdashboardAlertPost,
  apiSeedsdashboardStatementPost,
} from 'api/account';

class UserAddFormContainer extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      notificationMessage: null,
      notificationStatus: null,
      showPassword: false,
      success: false,
      loading: false,
    };

    this.closeNotification = this.closeNotification.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleClickShowPassword = this.handleClickShowPassword.bind(this);
    this.handleMouseDownPassword = this.handleMouseDownPassword.bind(this);
    this.buildObj = this.buildObj.bind(this);
  }

  closeNotification() {
    this.setState({ notificationMessage: null, notificationStatus: null });
  }

  // eslint-disable-next-line class-methods-use-this
  buildObj({ description, id }) {
    return {
      description,
      createdAt: new Date().toISOString().substring(0, 10),
      read: false,
      userId: id,
    };
  }

  async handleSubmit(user) {
    const { t, onCreate, config } = this.props;
    const { systemParams } = config || {};
    const { api } = systemParams || {};
    const bankingUrl = api && api[BANKING_API].url;
    const customerUrl = api && api[CUSTOMER_API].url;

    this.setState({ loading: true });
    try {
      const createdUser = await apiUserPost(customerUrl, user);

      let accountNumber = '';
      while (accountNumber.length !== 12) {
        accountNumber = `${Math.floor(100000 + Math.random() * 9000000000000)}`;
      }

      const checkingAccount = {
        accountNumber,
        balance: 350,
        userID: createdUser.id,
      };

      try {
        await apiSeedscardtransactionsAccountPost({ bankingUrl, checkingAccount });
        await apiSeedsdashboardAlertPost({
          bankingUrl,
          alertObj: this.buildObj({ description: 'Welcome to your bank', id: createdUser.id }),
        });
        await apiSeedsdashboardAlertPost({
          bankingUrl,
          alertObj: this.buildObj({ description: 'Checking account created', id: createdUser.id }),
        });
        await apiSeedsdashboardAlertPost({
          bankingUrl,
          alertObj: this.buildObj({ description: 'Internet banking ACTIVE', id: createdUser.id }),
        });
        await apiSeedsdashboardAlertPost({
          bankingUrl,
          alertObj: this.buildObj({ description: 'Info banking', id: createdUser.id }),
        });
        await apiSeedsdashboardStatementPost({
          bankingUrl,
          statementObj: this.buildObj({ description: 'First Statement', id: createdUser.id }),
        });
      } catch (err) {
        this.setState({ loading: false });
        this.handleError(err);
      }

      onCreate(createdUser);
      this.setState({
        notificationMessage: t('common.dataSaved'),
        notificationStatus: Notification.SUCCESS,
        success: true,
        loading: false,
      });
    } catch (err) {
      this.setState({ loading: false });
      this.handleError(err);
    }
  }

  handleError(err) {
    const { onError, t } = this.props;
    let errMessage = 'error.dataLoading';
    onError(err);
    if (err.message === '403') {
      errMessage = 'error.forbidden';
    }
    if (err.message === '409') {
      errMessage = 'error.alreadyExists';
    }
    this.setState({
      notificationMessage: t(errMessage),
      notificationStatus: Notification.ERROR,
    });
  }

  handleClickShowPassword() {
    this.setState(prevState => ({
      showPassword: !prevState.showPassword,
    }));
  }

  // eslint-disable-next-line class-methods-use-this
  handleMouseDownPassword(event) {
    event.preventDefault();
  }

  render() {
    const { notificationMessage, notificationStatus, showPassword, success, loading } = this.state;
    const { onLogin } = this.props;

    const Page = success
      ? React.createElement(AddUserSuccess, {
          onLogin,
        })
      : React.createElement(UserForm, {
          onSubmit: this.handleSubmit,
          showPassword,
          handleClickShowPassword: this.handleClickShowPassword,
          handleMouseDownPassword: this.handleMouseDownPassword,
        });

    return (
      // Remove?style={{ position: 'relative' }}
      <>
        {Page}
        <Notification
          status={notificationStatus}
          message={notificationMessage}
          onClose={this.closeNotification}
        />
        {loading && <Spinner />}
      </>
    );
  }
}

UserAddFormContainer.propTypes = {
  onError: PropTypes.func,
  onCreate: PropTypes.func,
  onLogin: PropTypes.func,
  t: PropTypes.func.isRequired,
  // eslint-disable-next-line react/forbid-prop-types
  config: PropTypes.object,
};

UserAddFormContainer.defaultProps = {
  onError: () => {},
  onCreate: () => {},
  onLogin: () => {},
  config: {},
};

export default withKeycloak(withTranslation()(UserAddFormContainer));
