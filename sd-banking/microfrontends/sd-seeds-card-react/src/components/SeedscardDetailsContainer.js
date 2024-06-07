import React from 'react';
import PropTypes from 'prop-types';
import { withTranslation } from 'react-i18next';
import { ThemeProvider } from '@material-ui/styles';
import { createMuiTheme } from '@material-ui/core';

import keycloakType from 'components/__types__/keycloak';
import { withKeycloak } from 'auth/KeycloakContext';
import { AuthenticatedView, UnauthenticatedView } from 'auth/KeycloakViews';
import SeedscardDetails from 'components/SeedscardDetails';
import Notification from 'components/common/Notification';
import { getSeedscardByUserID } from 'api/seedscard';

class SeedscardDetailsContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: true,
      account: null,
      notificationStatus: null,
      notificationMessage: null,
    };

    this.theme = createMuiTheme();
    this.closeNotification = this.closeNotification.bind(this);
    this.fetchData = this.fetchData.bind(this);
  }

  componentDidMount() {
    const { keycloak } = this.props;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    if (authenticated) {
      this.fetchData({ firstCall: true });
    }
  }

  componentDidUpdate(prevProps) {
    const { keycloak } = this.props;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    const changedAuth = prevProps.keycloak.authenticated !== authenticated;

    if (authenticated && changedAuth) {
      this.fetchData({ firstCall: true });
    }
  }

  fetchData({ firstCall }) {
    const { onError, t, keycloak, onDetail, config } = this.props;
    const { params } = config || {};
    const { cardname } = params || {};
    const authenticated = keycloak.initialized && keycloak.authenticated;
    const userID = keycloak.idTokenParsed.preferred_username;

    if (authenticated) {
      if (userID) {
        getSeedscardByUserID({ userID, cardname })
          .then(account => {
            this.setState({
              notificationStatus: null,
              notificationMessage: null,
              account,
            });
            if (cardname === 'checking' && firstCall) {
              onDetail({ cardname, accountID: account.id });
            }
          })
          .catch(e => {
            onError(e);
            // this.setState({
            //   notificationStatus: Notification.ERROR,
            //   notificationMessage: t('common.couldNotFetchData'),
            // });
          })
          .finally(() => this.setState({ loading: false }));
      } else {
        this.setState({
          loading: false,
          notificationStatus: Notification.ERROR,
          notificationMessage: t('common.missingId'),
        });
      }
    }
  }

  closeNotification() {
    this.setState({
      notificationStatus: null,
      notificationMessage: null,
    });
  }

  render() {
    const { account, notificationStatus, notificationMessage, loading } = this.state;
    const { t, keycloak, onDetail, config } = this.props;
    const { params } = config || {};
    const { cardname } = params || {};

    return (
      <ThemeProvider theme={this.theme}>
        <UnauthenticatedView keycloak={keycloak}>
          {t('common.notAuthenticated')}
        </UnauthenticatedView>
        <AuthenticatedView keycloak={keycloak}>
          {loading && t('common.loading')}
          {!loading && (
            <SeedscardDetails account={account} onDetail={onDetail} cardname={cardname} />
          )}
        </AuthenticatedView>
        <Notification
          status={notificationStatus}
          message={notificationMessage}
          onClose={this.closeNotification}
        />
      </ThemeProvider>
    );
  }
}

SeedscardDetailsContainer.propTypes = {
  onError: PropTypes.func,
  t: PropTypes.func.isRequired,
  keycloak: keycloakType.isRequired,
  onDetail: PropTypes.func.isRequired,
  config: PropTypes.string.isRequired,
};

SeedscardDetailsContainer.defaultProps = {
  onError: () => {},
};

export default withKeycloak(withTranslation()(SeedscardDetailsContainer));
