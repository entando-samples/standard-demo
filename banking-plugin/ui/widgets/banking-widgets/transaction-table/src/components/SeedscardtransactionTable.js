import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withTranslation } from 'react-i18next';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableRow from '@material-ui/core/TableRow';
import TableContainer from '@material-ui/core/TableContainer';

import seedscardtransactionType from 'components/__types__/seedscardtransaction';

// .pagination .page-item.active .page-link {
//   color: #000;
//   background: #D8EAA0;
//   border-radius: .125rem;
//   box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
//   transition: all 0.2s linear;
// }
// .pagination .page-item.active .page-link: hover, .pagination .page-item.active .page-link: focus {
//   color: #000;
//   background: #D8EAA0;
// }
const styles = {
  tableRoot: {
    marginTop: '10px',
    // minWidth: '100vw',
  },
  rowRoot: {
    cursor: 'pointer',
    overflow: 'hidden',
    textOverflow: 'ellipsis',
    whiteSpace: 'nowrap',
  },
  noItems: {
    margin: '15px',
    textAlign: 'center',
    color: '#15840C',
  },
  Table__notification: {
    display: 'inline-table',
    fontFamily: 'Cabin-Regular',
    '& thead': {
      background: '#D6E291',
      fontFamily: 'Cabin-Regular',
      '& tr': {
        textAlign: 'center',
      },
      '& th': {
        fontSize: '18px',
        lineHeight: '22px',
        color: '#15840C',
        paddingRight: '0',
        textTransform: 'uppercase',
      },
    },
    '& tr': {
      textAlign: 'center',
    },
  },
};

const StyledTableCell = withStyles(() => ({
  body: {
    fontSize: 14,
    borderRadius: 0,
    fontFamily: 'Cabin-Regular',
  },
}))(TableCell);

const StyledTableRow = withStyles(theme => ({
  root: {
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.background.default,
    },
  },
}))(TableRow);

// eslint-disable-next-line react/prefer-stateless-function
class SeedscardtransactionTable extends Component {
  render() {
    const { items, onSelect, classes, t, i18n } = this.props;

    const tableRows = items.map(item => (
      <StyledTableRow
        key={item.id}
        hover
        className={classes.rowRoot}
        onClick={() => onSelect(item)}
      >
        <StyledTableCell>{new Date(item.date).toLocaleDateString(i18n.language)}</StyledTableCell>
        <StyledTableCell align="left">{item.description}</StyledTableCell>
        <StyledTableCell align="center">
          ${item.amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
        </StyledTableCell>
        <StyledTableCell align="center">
          ${item.balance.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
        </StyledTableCell>
      </StyledTableRow>
    ));

    return items.length ? (
      <TableContainer className="table-wrapper-scroll-y my-custom-scrollbar">
        <Table className={classes.Table__notification} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell>
                {t('entities.seedscardtransaction.date').toUpperCase()}
              </StyledTableCell>
              <StyledTableCell align="left">
                {t('entities.seedscardtransaction.description').toUpperCase()}
              </StyledTableCell>
              <StyledTableCell align="center">
                {t('entities.seedscardtransaction.amount').toUpperCase()}
              </StyledTableCell>
              <StyledTableCell align="center">
                {t('entities.seedscardtransaction.balance').toUpperCase()}
              </StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>{tableRows}</TableBody>
        </Table>
      </TableContainer>
    ) : (
      <div className={classes.noItems}>{t('entities.seedscardtransaction.noItems')}</div>
    );
  }
}

SeedscardtransactionTable.propTypes = {
  items: PropTypes.arrayOf(seedscardtransactionType).isRequired,
  onSelect: PropTypes.func,
  classes: PropTypes.shape({
    rowRoot: PropTypes.string,
    tableRoot: PropTypes.string,
    noItems: PropTypes.string,
    Table__notification: PropTypes.string,
  }).isRequired,
  t: PropTypes.func.isRequired,
  i18n: PropTypes.shape({ language: PropTypes.string }).isRequired,
};

SeedscardtransactionTable.defaultProps = {
  onSelect: () => {},
};

export default withStyles(styles)(withTranslation()(SeedscardtransactionTable));
