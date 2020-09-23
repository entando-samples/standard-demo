import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withTranslation } from 'react-i18next';

import IconButton from '@material-ui/core/IconButton';
import RotateLeftIcon from '@material-ui/icons/RotateLeft';
import { withStyles } from '@material-ui/core/styles';

import keycloakType from 'components/__types__/keycloak';
import withKeycloak from 'auth/withKeycloak';
import { AuthenticatedView, UnauthenticatedView } from 'auth/KeycloakViews';
import ConfirmationDialogTrigger from 'components/common/ConfirmationDialogTrigger';
import PaginationWrapper from 'components/pagination/PaginationWrapper';
import withPagination from 'components/pagination/withPagination';
import ManageusersTable from 'components/ManageusersTable';
import Notification from 'components/common/Notification';
import {
  apiManageusersGet,
  apiAccountGet,
  apiAccountPost,
  apiTransactionsGet,
  apiTransactionsPost,
  apiNotificationsGet,
  apiNotificationPost,
  apiNotificationPut,
} from 'api/manageusers';
import { reducer, initialState } from 'state/manageusers.reducer';
import { ADD_FILTER, UPDATE_FILTER, DELETE_FILTER, CLEAR_FILTERS } from 'state/filter.types';
import { ERROR_FETCH, CLEAR_ERRORS, READ_ALL, CLEAR_ITEMS, RESET } from 'state/manageusers.types';
import {
  getRandomAccountNumber,
  getTransactionsList,
  getNotificationList,
  buildObj,
} from 'helpers/utils';

const styles = {
  fab: {
    float: 'right',
  },
  tableWrapper: {
    width: '100%',
    overflowX: 'auto',
    overflowY: 'hidden',
    marginTop: '80px',
  },
};

class ManageusersTableContainer extends Component {
  constructor(props) {
    super(props);

    this.state = initialState;

    this.handleReset = this.handleReset.bind(this);
    this.handleError = this.handleError.bind(this);
    this.closeNotification = this.closeNotification.bind(this);
    this.fetchData = this.fetchData.bind(this);
    this.updateFilter = this.updateFilter.bind(this);
    this.addFilter = this.addFilter.bind(this);
    this.removeFilter = this.removeFilter.bind(this);
    this.clearFilters = this.clearFilters.bind(this);
    this.applyFilters = this.applyFilters.bind(this);
    this.handleResetAccountAndTransactions = this.handleResetAccountAndTransactions.bind(this);
  }

  componentDidMount() {
    const { keycloak } = this.props;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    if (authenticated) {
      this.fetchUsers();
      // this.fetchData();
    }
  }

  componentDidUpdate(prevProps) {
    const { keycloak, paginationMode, pagination } = this.props;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    const changedAuth = prevProps.keycloak.authenticated !== authenticated;
    const changedPagination =
      ['pagination', 'infinite-scroll'].includes(paginationMode) &&
      (prevProps.pagination.currentPage !== pagination.currentPage ||
        prevProps.pagination.itemsPerPage !== pagination.itemsPerPage);

    if (authenticated && (changedAuth || changedPagination)) {
      this.fetchUsers();
      // this.fetchData();
    }
  }

  dispatch(action, afterSetState = () => {}) {
    this.setState(prevState => reducer(prevState, action), afterSetState);
  }

  async fetchData() {
    const { filters, items } = this.state;
    const { keycloak, paginationMode, pagination, serviceUrl } = this.props;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    if (authenticated) {
      try {
        const requestParameters = {
          filters,
          ...(paginationMode === ''
            ? {}
            : {
                pagination: {
                  page: pagination.currentPage,
                  rowsPerPage: pagination.itemsPerPage,
                },
              }),
        };

        const { manageusers, headers } = await apiManageusersGet(serviceUrl, requestParameters);
        const manageusersCount = (headers && headers['X-Total-Count']) || null;

        this.dispatch({
          type: READ_ALL,
          payload: {
            items: paginationMode === 'infinite-scroll' ? [...items, ...manageusers] : manageusers,
            count: manageusersCount,
          },
        });
      } catch (err) {
        this.handleError(err);
      }
    }
  }

  async fetchUsers() {
    const { keycloak, paginationMode, pagination, keycloakUrl, realm } = this.props;
    const { items } = this.state;
    fetch(
      `${keycloakUrl}/auth/admin/realms/${realm}/users?first=${pagination.currentPage *
        pagination.itemsPerPage}&max=${pagination.itemsPerPage}`,
      {
        headers: {
          Authorization: `Bearer ${keycloak.token}`,
        },
      }
    )
      .then(res => res.json())
      .then(response => {
        fetch(`${keycloakUrl}/auth/admin/realms/${realm}/users`, {
          headers: {
            Authorization: `Bearer ${keycloak.token}`,
          },
        })
          .then(r => r.json())
          .then(rs => {
            this.dispatch({
              type: READ_ALL,
              payload: {
                items: paginationMode === 'infinite-scroll' ? [...items, ...response] : response,
                count: rs.length,
              },
            });
          });
      })
      .catch(err => this.handleError(err));
  }

  updateFilter(filterId, values) {
    this.dispatch({ type: UPDATE_FILTER, payload: { values, filterId } });
  }

  addFilter() {
    this.dispatch({ type: ADD_FILTER });
  }

  removeFilter(filterId) {
    this.dispatch({ type: DELETE_FILTER, payload: { filterId } }, this.fetchData);
  }

  clearFilters() {
    this.dispatch({ type: CLEAR_FILTERS }, this.fetchData);
  }

  applyFilters() {
    this.dispatch({ type: CLEAR_ITEMS }, this.fetchData);
  }

  closeNotification() {
    this.dispatch({ type: CLEAR_ERRORS });
  }

  handleError(err) {
    const { onError, t } = this.props;
    onError(err);
    this.dispatch({
      type: ERROR_FETCH,
      payload: {
        notificationMessage: t('error.dataLoading'),
        notificationStatus: Notification.ERROR,
      },
    });
  }

  /**
   * Check if ACCOUNT exists, if not create it. Then check if TRANSACTIONS exist, if not create them;
   * @param accountName
   * @param serviceUrl
   * @param item
   * @returns {Promise<void>}
   */
  // eslint-disable-next-line class-methods-use-this
  async handleResetAccountAndTransactions({ accountName, serviceUrl, item }) {
    let account = await apiAccountGet({
      serviceUrl,
      userid: item.id,
      account: accountName,
    });

    // eslint-disable-next-line prefer-destructuring
    if (account && account.length && account.length > 0) account = account[0];
    if (!account || (account && account.length < 1)) {
      const accountObj = {
        accountNumber: getRandomAccountNumber(),
        balance: 3500,
        userID: item.id,
      };
      account = await apiAccountPost({
        serviceUrl,
        account: accountName,
        accountObj,
      });
    }
    let transactions = await apiTransactionsGet({
      serviceUrl,
      accountid: account.id,
      account: accountName,
    });
    if (!transactions || (transactions && transactions < 1)) {
      const transactionsList = getTransactionsList({ b: 3500, accountID: account.id });

      transactions = transactionsList.map(
        async transaction =>
          // eslint-disable-next-line no-return-await
          await apiTransactionsPost({ serviceUrl, account: accountName, transaction })
      );
    }
  }

  /**
   * Check if NOTIFICATIONS exist, if not create them;
   */
  // eslint-disable-next-line class-methods-use-this
  async handleResetNotifications({ notificationName, serviceUrl, item }) {
    let notifications = await apiNotificationsGet({
      serviceUrl,
      userid: item.id,
      notificationName,
    });
    if (!notifications || (notifications && notifications.length < 1)) {
      if (notificationName === 'alert') {
        const notificationList = getNotificationList({ userId: item.id });

        notifications = notificationList.map(
          // eslint-disable-next-line no-return-await
          async notification =>
            // eslint-disable-next-line no-return-await
            await apiNotificationPost({
              serviceUrl,
              notificationName,
              notificationObj: notification,
            })
        );
      } else {
        const notification = buildObj({ description: 'First Statement', id: item.id });
        await apiNotificationPost({ serviceUrl, notificationName, notificationObj: notification });
      }
    } else {
      notifications.map(async notification => {
        const n = { ...notification };
        n.read = false;
        await apiNotificationPut({ serviceUrl, notificationName, notificationObj: n });
      });
    }
  }

  async handleReset(item) {
    const { t, onReset, keycloak, serviceUrl } = this.props;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    if (authenticated) {
      try {
        const checking = 'checking';
        const savings = 'savings';
        const creditcard = 'creditcard';
        const alert = 'alert';
        const statement = 'statement';

        await this.handleResetAccountAndTransactions({ accountName: checking, serviceUrl, item });
        await this.handleResetAccountAndTransactions({ accountName: savings, serviceUrl, item });
        await this.handleResetAccountAndTransactions({ accountName: creditcard, serviceUrl, item });
        await this.handleResetNotifications({ notificationName: alert, serviceUrl, item });
        await this.handleResetNotifications({ notificationName: statement, serviceUrl, item });

        onReset(item);
        this.dispatch({ type: RESET, payload: { id: item.id } });
        this.setState({
          notificationMessage: t('common.dataResetted'),
          notificationStatus: Notification.SUCCESS,
        });
      } catch (err) {
        this.handleError(err);
        this.setState({
          notificationMessage: t('common.couldNotFetchData'),
          notificationStatus: Notification.ERROR,
        });
      }
    }
  }

  handleConfirmationDialogAction(action, item) {
    switch (action) {
      case ConfirmationDialogTrigger.CONFIRM: {
        this.handleReset(item);
        break;
      }
      default:
        break;
    }
  }

  render() {
    const { items, count, notificationMessage, notificationStatus } = this.state;
    const { classes, onSelect, onReset, t, keycloak, paginationMode = '' } = this.props;
    const resetLabel = t('common.reset');

    const Actions = ({ item }) =>
      onReset ? (
        <ConfirmationDialogTrigger
          onCloseDialog={action => this.handleConfirmationDialogAction(action, item)}
          dialog={{
            title: t('entities.manageusers.resetDialog.title'),
            description: t('entities.manageusers.resetDialog.description'),
            confirmLabel: t('common.yes'),
            discardLabel: t('common.no'),
          }}
          Renderer={({ onClick }) => (
            <IconButton aria-label={resetLabel} title={resetLabel} onClick={onClick}>
              <RotateLeftIcon />
            </IconButton>
          )}
        />
      ) : (
        ''
      );

    return (
      <>
        <UnauthenticatedView keycloak={keycloak}>
          {t('common.notAuthenticated')}
        </UnauthenticatedView>
        <AuthenticatedView keycloak={keycloak}>
          <PaginationWrapper items={items} paginationMode={paginationMode} count={count}>
            <div className={classes.tableWrapper}>
              <ManageusersTable items={items} onSelect={onSelect} Actions={Actions} />
            </div>
          </PaginationWrapper>
        </AuthenticatedView>
        <Notification
          status={notificationStatus}
          message={notificationMessage}
          onClose={this.closeNotification}
        />
      </>
    );
  }
}

ManageusersTableContainer.propTypes = {
  classes: PropTypes.shape({
    fab: PropTypes.string,
    tableWrapper: PropTypes.string,
  }).isRequired,
  onError: PropTypes.func,
  onSelect: PropTypes.func,
  onReset: PropTypes.func,
  t: PropTypes.func.isRequired,
  keycloak: keycloakType.isRequired,
  paginationMode: PropTypes.string,
  pagination: PropTypes.shape({
    currentPage: PropTypes.number,
    itemsPerPage: PropTypes.number,
  }),
  serviceUrl: PropTypes.string,
  keycloakUrl: PropTypes.string,
  realm: PropTypes.string,
};

ManageusersTableContainer.defaultProps = {
  onReset: () => {},
  onError: () => {},
  onSelect: () => {},
  paginationMode: '',
  pagination: null,
  serviceUrl: '',
  keycloakUrl: '',
  realm: '',
};

export default withKeycloak(
  withStyles(styles)(
    withTranslation(undefined, { withRef: true })(withPagination(ManageusersTableContainer))
  )
);
