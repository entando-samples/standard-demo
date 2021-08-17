import React from 'react';
import PropTypes from 'prop-types';
import InfiniteScroll from 'react-infinite-scroll-component';
import { withTranslation } from 'react-i18next';
import TablePagination from '@material-ui/core/TablePagination';

import {
  withPaginationContext,
  itemsPerPageOptions,
} from 'components/pagination/PaginationContext';
import TablePaginationActions from 'components/pagination/TablePaginationActions';

import seedscardtransactionType from 'components/__types__/seedscardtransaction';

const PaginationWrapper = ({ children, pagination, paginationMode, items, itemCount, t }) => {
  if (paginationMode === 'infinite-scroll') {
    return (
      <InfiniteScroll
        dataLength={items.length}
        next={() => pagination.onChangeCurrentPage(pagination.currentPage + 1)}
        hasMore={items.length < itemCount}
        loader={<div>{t('common.loadingMore')}</div>}
      >
        {children}
      </InfiniteScroll>
    );
  }
  if (paginationMode === 'pagination') {
    return (
      <>
        {children}
        {itemCount > 0 && (
          <TablePagination
            component="div"
            rowsPerPageOptions={itemsPerPageOptions}
            count={itemCount}
            rowsPerPage={pagination.itemsPerPage}
            page={pagination.currentPage}
            SelectProps={{
              native: true,
            }}
            onChangePage={pagination.onChangeCurrentPage}
            onChangeRowsPerPage={pagination.onChangeItemsPerPage}
            ActionsComponent={TablePaginationActions}
          />
        )}
      </>
    );
  }
  return children;
};

PaginationWrapper.propTypes = {
  children: PropTypes.node,
  pagination: PropTypes.shape({
    itemsPerPage: PropTypes.number,
    currentPage: PropTypes.number,
  }),
  paginationMode: PropTypes.string,
  items: PropTypes.arrayOf(seedscardtransactionType).isRequired,
  itemCount: PropTypes.number,
  t: PropTypes.func.isRequired,
};

export default withTranslation()(withPaginationContext(PaginationWrapper));
