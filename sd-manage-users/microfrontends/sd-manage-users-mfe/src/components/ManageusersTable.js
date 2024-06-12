import React from 'react';
import PropTypes from 'prop-types';
import { withTranslation } from 'react-i18next';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableRow from '@material-ui/core/TableRow';

import manageusersType from 'components/__types__/manageusers';

const styles = {
  tableRoot: {
    marginTop: '10px',
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
        height: '50px',
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
    padding: '0 2rem',
  },
}))(TableCell);

const StyledTableRow = withStyles(theme => ({
  root: {
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.background.default,
    },
  },
}))(TableRow);

const ManageusersTable = ({ items, onSelect, classes, t, Actions }) => {
  const tableRows = items.map((item, idx) => (
    // eslint-disable-next-line react/no-array-index-key
    <StyledTableRow hover className={classes.rowRoot} key={idx} onClick={() => onSelect(item)}>
      <StyledTableCell>
        <span>{item.username}</span>
      </StyledTableCell>
      <StyledTableCell>
        <span>{item.firstName}</span>
      </StyledTableCell>
      <StyledTableCell>
        <span>{item.lastName}</span>
      </StyledTableCell>
      <StyledTableCell>
        <span>{item.email}</span>
      </StyledTableCell>
      {Actions && (
        <StyledTableCell>
          <Actions item={item} />
        </StyledTableCell>
      )}
    </StyledTableRow>
  ));

  return items.length ? (
    <Table className={classes.Table__notification} stickyHeader>
      <TableHead>
        <TableRow>
          <StyledTableCell>
            <span>{t('entities.manageusers.username')}</span>
          </StyledTableCell>
          <StyledTableCell>
            <span>{t('entities.manageusers.firstName')}</span>
          </StyledTableCell>
          <StyledTableCell>
            <span>{t('entities.manageusers.lastName')}</span>
          </StyledTableCell>
          <StyledTableCell>
            <span>{t('entities.manageusers.email')}</span>
          </StyledTableCell>
          {Actions && (
            <StyledTableCell>
              <span>{t('common.reset')}</span>
            </StyledTableCell>
          )}
        </TableRow>
      </TableHead>
      <TableBody>{tableRows}</TableBody>
    </Table>
  ) : (
    <div className={classes.noItems}>{t('entities.manageusers.noItems')}</div>
  );
};

ManageusersTable.propTypes = {
  items: PropTypes.arrayOf(manageusersType).isRequired,
  onSelect: PropTypes.func,
  classes: PropTypes.shape({
    rowRoot: PropTypes.string,
    tableRoot: PropTypes.string,
    noItems: PropTypes.string,
    Table__notification: PropTypes.string,
  }).isRequired,
  t: PropTypes.func.isRequired,
  Actions: PropTypes.oneOfType([PropTypes.string, PropTypes.func]),
};

ManageusersTable.defaultProps = {
  onSelect: () => {},
  Actions: null,
};

export default withStyles(styles)(withTranslation()(ManageusersTable));
