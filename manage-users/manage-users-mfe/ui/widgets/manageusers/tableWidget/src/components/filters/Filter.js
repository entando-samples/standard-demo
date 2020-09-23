import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import { withTranslation } from 'react-i18next';
import Select from '@material-ui/core/Select';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import IconButton from '@material-ui/core/IconButton';
import DeleteForever from '@material-ui/icons/DeleteForever';

import filterType from 'components/__types__/filter';
import { getFieldFilterTypes } from 'components/filters/utils';

const styles = () => ({
  formControl: {
    minWidth: 120,
    width: '90%',
  },
  icon: {
    color: 'rgba(0, 0, 0, 0.54)',
  },
});

const Filter = ({ filter, t, update, remove, filterId, classes }) => {
  const filterOperators = getFieldFilterTypes(filter.field);

  const handleChange = event => {
    update(filterId, { [event.target.name]: event.target.value });
  };

  const handleRemoveClick = () => {
    remove(filterId);
  };

  return (
    <Grid container>
      <Grid item xs={5}>
        <FormControl className={classes.formControl}>
          <InputLabel id={`${filterId}-field-label`}>{t('filters.field')}</InputLabel>
          <Select
            native
            labelId={`${filterId}-field-label`}
            id={`${filterId}-field`}
            name="field"
            value={filter.field}
            onChange={handleChange}
          >
            <option value="" />
            <option value="username">{t('entities.manageusers.username')}</option>
          </Select>
        </FormControl>
      </Grid>
      <Grid item xs={3}>
        {!!filter.field && (
          <FormControl className={classes.formControl}>
            <InputLabel id={`${filterId}-operator-label`}>{t('filters.operator')}</InputLabel>
            <Select
              native
              labelId={`${filterId}-operator-label`}
              id={`${filterId}-operator`}
              name="operator"
              value={filter.operator}
              onChange={handleChange}
            >
              <option value="" />
              {filterOperators.map(operator => {
                if (typeof operator !== 'string') {
                  return (
                    <option key={operator.value} value={operator.value}>
                      {operator.title}
                    </option>
                  );
                }
                return (
                  <option key={operator} value={operator}>
                    {t(`filters.operators.${operator}`)}
                  </option>
                );
              })}
            </Select>
          </FormControl>
        )}
      </Grid>
      <Grid item xs={3}>
        {!!filter.operator && !['specified', 'unspecified'].includes(filter.operator) && (
          <FormControl className={classes.formControl}>
            <TextField
              disabled={!filter.field}
              id="standard-required"
              name="value"
              value={filter.value}
              onChange={handleChange}
              margin="normal"
            />
          </FormControl>
        )}
      </Grid>
      <Grid item xs={1}>
        <IconButton aria-label="Remove filter" className={classes.icon} onClick={handleRemoveClick}>
          <DeleteForever />
        </IconButton>
      </Grid>
    </Grid>
  );
};

Filter.propTypes = {
  classes: PropTypes.shape({
    formControl: PropTypes.string,
    icon: PropTypes.string,
  }).isRequired,
  t: PropTypes.func.isRequired,
  update: PropTypes.func.isRequired,
  remove: PropTypes.func.isRequired,
  filter: filterType.isRequired,
  filterId: PropTypes.number.isRequired,
};

export default withStyles(styles, { withTheme: true })(withTranslation()(Filter));
