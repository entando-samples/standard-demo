import React from 'react';
import App from 'App';
import PropTypes from 'prop-types';
import { withTranslation } from 'react-i18next';
import { ThemeProvider } from '@material-ui/styles';
import keycloakType from 'components/__types__/keycloak';
import { withKeycloak } from 'auth/KeycloakContext';
import { AuthenticatedView, UnauthenticatedView } from 'auth/KeycloakViews';
import Notification from 'components/common/Notification';

import { getApiContext, putApiContext } from 'api/apiHandler';

class AppContainer extends React.PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      modalOpen: false,
      loading: true,
      notificationStatus: null,
      notificationMessage: null,
      descriptionFilter: null,
      rangeFilter: null,
    };
    this.toggleModal = this.toggleModal.bind(this);
    this.putDocumentRead = this.putDocumentRead.bind(this);
    this.closeNotification = this.closeNotification.bind(this);
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.onChangeRangeFilter = this.onChangeRangeFilter.bind(this);
  }

  componentDidMount() {
    const { keycloak } = this.props;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    if (authenticated) {
      this.onMount();
    }
  }

  componentDidUpdate(prevProps, prevState) {
    const { keycloak } = this.props;
    const { descriptionFilter, rangeFilter } = this.state;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    const changedAuth = prevProps.keycloak.authenticated !== authenticated;
    const changedDescriptionFilter = prevState.descriptionFilter !== descriptionFilter;
    const changedRangeFilter = prevState.rangeFilter !== rangeFilter;

    if (authenticated && (changedAuth || changedDescriptionFilter || changedRangeFilter)) {
      this.onMount();
    }
  }

  onMount = () => {
    const { icon, onError, t, keycloak } = this.props;
    const { descriptionFilter, rangeFilter } = this.state;
    const apiCall = getApiContext(icon);
    const authenticated = keycloak.initialized && keycloak.authenticated;
    const userId = keycloak.idTokenParsed.preferred_username;

    const filters = [
      {
        field: 'userId',
        operator: 'equals',
        value: userId,
      },
    ];

    if (descriptionFilter) {
      filters.push({
        field: 'description',
        operator: 'contains',
        value: descriptionFilter,
      });
    }

    if (rangeFilter) {
      filters.push({
        field: 'createdAt',
        operator: 'lessThanOrEqual',
        value: rangeFilter.dateB,
      });
      filters.push({
        field: 'createdAt',
        operator: 'greaterThanOrEqual',
        value: rangeFilter.dateA,
      });
    }

    const requestParameters = {
      filters,
      // ...{
      //   pagination: {
      //     page: pagination.currentPage,
      //     rowsPerPage: pagination.itemsPerPage,
      //   },
      // },
    };

    if (authenticated) {
      if (userId) {
        apiCall(requestParameters)
          .then(response => {
            const { data } = response;
            const sortData = data.slice().sort((a, b) => {
              const aDate = new Date(a.createdAt);
              const bDate = new Date(b.createdAt);
              let rv = bDate - aDate;
              if (rv === 0) {
                rv = a.description.localeCompare(b.description);
              }
              return rv;
              // return new Date(b.createdAt) - new Date(a.createdAt);
            });
            this.setState({ data: sortData });
          })
          .catch(e => {
            onError(e);
            this.setState({
              notificationStatus: Notification.ERROR,
              notificationMessage: t('common.couldNotFetchData'),
            });
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
  };

  onChangeDescription(description) {
    this.setState({
      descriptionFilter: description,
    });
  }

  onChangeRangeFilter(range) {
    this.setState({
      rangeFilter: range,
    });
  }

  closeNotification() {
    this.setState({
      notificationStatus: null,
      notificationMessage: null,
    });
  }

  async putDocumentRead(document) {
    const { icon, t } = this.props;
    const apiCall = putApiContext(icon);
    try {
      const updated = await apiCall(document);
      if (updated) this.onMount();
    } catch (e) {
      this.setState({
        notificationStatus: Notification.ERROR,
        notificationMessage: t('common.couldNotFetchData'),
      });
    }
  }

  toggleModal() {
    this.setState(prevState => ({
      modalOpen: !prevState.modalOpen,
    }));
  }

  render() {
    const { icon, title, keycloak, t } = this.props;
    const { modalOpen, data, loading, notificationStatus, notificationMessage } = this.state;
    return (
      <ThemeProvider theme={this.theme}>
        <UnauthenticatedView keycloak={keycloak}>
          {t('common.notAuthenticated')}
        </UnauthenticatedView>
        <AuthenticatedView keycloak={keycloak}>
          {loading && t('common.loading')}
          {!loading && (
            <App
              icon={icon}
              title={title}
              modalOpen={modalOpen}
              toggleModal={this.toggleModal}
              data={data}
              putDocumentRead={this.putDocumentRead}
              onChangeDescription={this.onChangeDescription}
              onChangeRangeFilter={this.onChangeRangeFilter}
            />
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

AppContainer.propTypes = {
  icon: PropTypes.string,
  title: PropTypes.string,
  onError: PropTypes.func,
  keycloak: keycloakType.isRequired,
  t: PropTypes.func.isRequired,
};

AppContainer.defaultProps = {
  icon: '',
  title: '',
  onError: () => {},
};

export default withKeycloak(withTranslation()(AppContainer));
