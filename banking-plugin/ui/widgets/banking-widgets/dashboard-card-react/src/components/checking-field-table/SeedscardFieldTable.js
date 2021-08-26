import React from 'react';
import PropTypes from 'prop-types';
import { withTranslation } from 'react-i18next';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

import seedscardType from 'components/__types__/seedscard';

const SeedscardFieldTable = ({ t, seedscard }) => {
  const translationKeyPrefix = `entities.seedscard.`;

  return (
    <Table>
      <TableHead>
        <TableRow>
          <TableCell>{t('common.name')}</TableCell>
          <TableCell>{t('common.value')}</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        <TableRow>
          <TableCell>
            <span>{t(`${translationKeyPrefix}id`)}</span>
          </TableCell>
          <TableCell>
            <span>{seedscard.id}</span>
          </TableCell>
        </TableRow>
        <TableRow>
          <TableCell>
            <span>{t(`${translationKeyPrefix}accountNumber`)}</span>
          </TableCell>
          <TableCell>
            <span>{seedscard.accountNumber}</span>
          </TableCell>
        </TableRow>
        <TableRow>
          <TableCell>
            <span>{t(`${translationKeyPrefix}balance`)}</span>
          </TableCell>
          <TableCell>
            <span>{seedscard.balance}</span>
          </TableCell>
        </TableRow>
        <TableRow>
          <TableCell>
            <span>{t(`${translationKeyPrefix}userId`)}</span>
          </TableCell>
          <TableCell>
            <span>{seedscard.userId}</span>
          </TableCell>
        </TableRow>
      </TableBody>
    </Table>
  );
};

SeedscardFieldTable.propTypes = {
  seedscard: seedscardType,
  t: PropTypes.func.isRequired,
};

SeedscardFieldTable.defaultProps = {
  seedscard: [],
};

export default withTranslation()(SeedscardFieldTable);
