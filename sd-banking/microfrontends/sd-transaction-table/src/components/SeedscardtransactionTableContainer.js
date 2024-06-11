import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withTranslation } from 'react-i18next';

import { withStyles } from '@material-ui/core/styles';

import keycloakType from 'components/__types__/keycloak';
import { withKeycloak } from 'auth/KeycloakContext';
import { AuthenticatedView, UnauthenticatedView } from 'auth/KeycloakViews';
import ConfirmationDialogTrigger from 'components/common/ConfirmationDialogTrigger';
import PaginationWrapper from 'components/pagination/PaginationWrapper';
import { withPaginationContext } from 'components/pagination/PaginationContext';
import SeedscardtransactionTable from 'components/SeedscardtransactionTable';
import Notification from 'components/common/Notification';
import {
  apiSeedscardtransactionsGet,
  apiSeedscardtransactionsDelete,
} from 'api/seedscardtransactions';
import { reducer, initialState } from 'state/seedscardtransaction.reducer';
import {
  ADD_FILTER,
  UPDATE_FILTER,
  DELETE_FILTER,
  CLEAR_FILTERS,
  SET_DESCRIPTION_FILTER,
  SET_RANGE_FILTER,
} from 'state/filter.types';
import {
  DELETE,
  ERROR_FETCH,
  CLEAR_ERRORS,
  READ_ALL,
  CLEAR_ITEMS,
} from 'state/seedscardtransaction.types';
import {
  getDateFromRange,
  getStartMonthFromDate,
  parseDateToString,
} from 'components/filters/utils';

const styles = {
  fab: {
    float: 'right',
  },
  tableWrapper: {
    overflowX: 'auto',
    overflowY: 'hidden',
  },
  tableTitle: {
    textTransform: 'capitalize',
    color: '#0E6837',
    fontSize: '30px',
    fontStyle: 'normal',
    fontWeight: '500',
    margin: 'auto 0',
    height: '50px',
  },
  SearchInput: {
    border: '1px solid #ced4da',
    position: 'absolute',
    width: '25%',
    right: 'calc(100px + 1rem)',
    margin: '10px 0',
    height: '33px',
    fontSize: '14px',
    '@media (max-width: 1024px ) and (min-width: 320px) and (orientation: portrait )': {
      top: '105px',
      width: '63%',
    },
  },
  SearchButton: {
    background: '#C0E570',
    border: 'none',
    color: '#0E6837',
    fontStyle: 'normal',
    fontWeight: '400',
    fontSize: '14px',
    width: '100px',
    textTransform: 'capitalize',
    padding: '.1em',
    height: '33px',
    margin: '10px 1rem',
    position: 'absolute',
    right: '0',
    '@media (max-width: 1024px ) and (min-width: 320px) and (orientation: portrait )': {
      top: '105px',
    },
  },
  tableTitleRow: {
    display: 'flex',
    margin: '1rem 1rem 1rem 0',
    '@media (max-width: 1024px ) and (min-width: 320px) and (orientation: portrait )': {
      flexDirection: 'column',
      height: '200px',
      margin: '1rem 0',
    },
    // '@media (min-width: 320px) and (max-width: 1024px)  and (orientation: landscape )': {
    //   fontSize: '1.5rem',
    // },
  },
  SelectRange: {
    border: 'none',
    borderBottom: '1px solid #c7c7c7',
    height: '33px',
    color: '#c7c7c7',
    margin: 'auto 1rem',
    '&:focus': {
      outline: 'none',
    },
    '& option': {
      '&:hover': {
        background: '#C0E570',
        color: '#0E6837',
      },
    },
  },
};

class SeedscardtransactionTableContainer extends Component {
  constructor(props) {
    super(props);

    this.state = initialState;

    this.handleDelete = this.handleDelete.bind(this);
    this.handleError = this.handleError.bind(this);
    this.closeNotification = this.closeNotification.bind(this);
    this.fetchData = this.fetchData.bind(this);
    this.updateFilter = this.updateFilter.bind(this);
    this.addFilter = this.addFilter.bind(this);
    this.removeFilter = this.removeFilter.bind(this);
    this.clearFilters = this.clearFilters.bind(this);
    this.applyFilters = this.applyFilters.bind(this);
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.onChangeRangeFilter = this.onChangeRangeFilter.bind(this);
  }

  componentDidMount() {
    const { keycloak } = this.props;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    if (authenticated) {
      this.fetchData();
    }
  }

  componentDidUpdate(prevProps, prevState) {
    const { rangeFilter } = this.state;
    const { keycloak, paginationMode, pagination, detail } = this.props;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    const changedAuth = prevProps.keycloak.authenticated !== authenticated;
    const changedPagination =
      ['pagination', 'infinite-scroll'].includes(paginationMode) &&
      (prevProps.pagination.currentPage !== pagination.currentPage ||
        prevProps.pagination.itemsPerPage !== pagination.itemsPerPage);
    const changedDetail = prevProps.detail !== detail;
    const rangeFilterChanged =
      JSON.stringify(prevState.rangeFilter) !== JSON.stringify(rangeFilter);

    if (
      authenticated &&
      (changedAuth || changedPagination || changedDetail || rangeFilterChanged)
    ) {
      this.fetchData();
    }
  }

  // eslint-disable-next-line react/sort-comp
  dispatch(action, afterSetState = () => {}) {
    this.setState(prevState => reducer(prevState, action), afterSetState);
  }

  async fetchData() {
    const { items, descriptionFilter, rangeFilter } = this.state;
    const { keycloak, paginationMode, pagination, detail, config } = this.props;
    const { systemParams } = config || {};
    const { api } = systemParams || {};
    const baseurl = api && api['sd-banking-api'].url;
    const authenticated = keycloak.initialized && keycloak.authenticated;
    const receivedDetail = detail && detail.cardname && detail.accountID;

    if (authenticated && receivedDetail) {
      try {
        const filters = [
          {
            field: 'accountID',
            operator: 'equals',
            value: detail.accountID.toString(),
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
            field: 'date',
            operator: 'lessThanOrEqual',
            value: rangeFilter.dateB,
          });
          filters.push({
            field: 'date',
            operator: 'greaterThanOrEqual',
            value: rangeFilter.dateA,
          });
        }

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
          cardname: detail.cardname,
          baseurl,
        };

        const { seedscardtransactions, headers } = await apiSeedscardtransactionsGet(
          requestParameters
        );
        const seedscardtransactionCount = (headers && headers['X-Total-Count']) || null;

        this.dispatch({
          type: READ_ALL,
          payload: {
            items:
              paginationMode === 'infinite-scroll'
                ? [...items, ...seedscardtransactions]
                : seedscardtransactions,
            count: seedscardtransactionCount,
          },
        });
      } catch (err) {
        this.handleError(err);
        const seedscardtransactionCount = null;

        this.dispatch({
          type: READ_ALL,
          payload: {
            items: paginationMode === 'infinite-scroll' ? [...items, ...[]] : [],
            count: seedscardtransactionCount,
          },
        });
      }
    }
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
        message: t('error.dataLoading'),
        status: Notification.ERROR,
      },
    });
  }

  async handleDelete(item) {
    const { onDelete, keycloak, config } = this.props;
    const { systemParams } = config || {};
    const { api } = systemParams || {};
    const baseurl = api && api['sd-banking-api'].url;
    const authenticated = keycloak.initialized && keycloak.authenticated;

    if (authenticated) {
      try {
        await apiSeedscardtransactionsDelete(baseurl, item.id);
        onDelete(item);
        this.dispatch({ type: DELETE, payload: { id: item.id } });
      } catch (err) {
        this.handleError(err);
      }
    }
  }

  handleConfirmationDialogAction(action, item) {
    switch (action) {
      case ConfirmationDialogTrigger.CONFIRM: {
        this.handleDelete(item);
        break;
      }
      default:
        break;
    }
  }

  onChangeDescription(evt) {
    const { pagination } = this.props;
    pagination.currentPage = 0;
    this.dispatch({ type: SET_DESCRIPTION_FILTER, payload: evt.target.value });
  }

  onChangeRangeFilter(evt) {
    // const { rangeFilter } = this.state;
    const { pagination } = this.props;
    pagination.currentPage = 0;
    const today = new Date();

    let dateA = parseDateToString({ date: today });
    const dateB = parseDateToString({ date: today });

    let range = {
      dateA,
      dateB,
    };

    switch (evt.target.value) {
      case '90': {
        dateA = getDateFromRange({ d: new Date(), range: 90 });
        range.dateA = dateA;
        break;
      }
      case 'month': {
        dateA = getStartMonthFromDate({ d: new Date() });
        range.dateA = dateA;
        break;
      }
      default: {
        range = null;
      }
    }

    this.dispatch({ type: SET_RANGE_FILTER, payload: range });
  }

  render() {
    const { items, itemCount, errorMessage, errorStatus, descriptionFilter } = this.state;
    const { classes, onSelect, t, keycloak, paginationMode = '', detail } = this.props;

    return (
      <>
        <UnauthenticatedView keycloak={keycloak}>
          {t('common.notAuthenticated')}
        </UnauthenticatedView>
        <AuthenticatedView keycloak={keycloak}>
          <PaginationWrapper items={items} paginationMode={paginationMode} itemCount={itemCount}>
            <div className={classes.tableTitleRow}>
              <span className={classes.tableTitle}>{detail.cardname} Transactions</span>
              <select
                name="transactionRange"
                id="transactionRange"
                className={classes.SelectRange}
                onChange={this.onChangeRangeFilter}
              >
                <option value="all">All transactions</option>
                <option value="month">Last month</option>
                <option value="90">Last 90 days</option>
              </select>
              <input
                className={classes.SearchInput}
                type="text"
                onChange={this.onChangeDescription}
                value={descriptionFilter}
              />
              <button className={classes.SearchButton} type="button" onClick={this.fetchData}>
                Filter
              </button>
            </div>
            <div className={classes.tableWrapper}>
              <SeedscardtransactionTable items={items} onSelect={onSelect} />
            </div>
          </PaginationWrapper>
        </AuthenticatedView>
        <Notification
          status={errorStatus}
          message={errorMessage}
          onClose={this.closeNotification}
        />
      </>
    );
  }
}

SeedscardtransactionTableContainer.propTypes = {
  classes: PropTypes.shape({
    fab: PropTypes.string,
    tableWrapper: PropTypes.string,
    tableTitle: PropTypes.string,
    SearchInput: PropTypes.string,
    tableTitleRow: PropTypes.string,
    SearchButton: PropTypes.string,
    SelectRange: PropTypes.string,
  }).isRequired,
  onError: PropTypes.func,
  onSelect: PropTypes.func,
  onDelete: PropTypes.func,
  t: PropTypes.func.isRequired,
  keycloak: keycloakType.isRequired,
  paginationMode: PropTypes.string,
  pagination: PropTypes.shape({
    currentPage: PropTypes.number,
    itemsPerPage: PropTypes.number,
  }),
  detail: PropTypes.shape({
    cardname: PropTypes.string,
    accountID: PropTypes.string,
  }),
  // eslint-disable-next-line react/forbid-prop-types
  config: PropTypes.object.isRequired,
};

SeedscardtransactionTableContainer.defaultProps = {
  onDelete: () => {},
  onError: () => {},
  onSelect: () => {},
  paginationMode: '',
  pagination: null,
  detail: {
    cardname: '',
    accountID: '',
  },
};

export default withKeycloak(
  withStyles(styles)(
    withTranslation(undefined, { withRef: true })(
      withPaginationContext(SeedscardtransactionTableContainer)
    )
  )
);
