import React from 'react';
import PropTypes from 'prop-types';
import { withTranslation } from 'react-i18next';
import { withStyles } from '@material-ui/core/styles';
import { compose } from 'recompose';
import seedscardType from 'components/__types__/seedscard';
import seedscardIcon from 'assets/Framecredit-card.png';

const styles = () => ({
  SeedsCard: {
    display: 'block !important',
    height: '300px',
    background: '#ffffff',
    padding: '10%',
    boxShadow: '0 0 18px rgba(0, 0, 0, 0.25)',
    borderRadius: '20px',
    transition: '0.3s ease',
    '&:hover': {
      height: '300px',
      background: '#7ab7740d',
      boxShadow: '0 0 18px #7ab774',
      padding: '10%',
      borderRadius: '20px',
    },
  },
  SeedsCard__icon: {},
  SeedsCard__header: {
    display: 'flex',
    alignItems: 'baseline',
    position: 'relative',
    '& *': {
      backgroundColor: 'transparent !important',
    },
  },
  SeedsCard__title: {
    fontWeight: '500',
    fontSize: '30px',
    color: '#0e6837',
    margin: '0 20px',
    lineHeight: '35px',
  },
  SeedsCard__value: {
    fontSize: '16px',
    color: '#c7c7c7',
    lineHeight: '33px',
  },
  SeedsCard__action: {
    position: 'absolute',
    right: '0',
    top: '19%',
    color: '#c7c7c7',
  },
  SeedsCard__balance: {
    fontWeight: '500',
    fontSize: '50px',
    color: '#000000',
    margin: '10% 0 0 0',
  },
  SeedsCard__balanceCaption: {
    fontWeight: '500',
    fontSize: '18px',
    color: '#c7c7c7',
  },
  SeedsCard__balanceReward: {
    fontWeight: '500',
    fontSize: '18px',
    color: '#c7c7c7',
  },
  SeedsCard__balanceRewardValue: {
    fontWeight: '500',
    fontSize: '18px',
    color: '#000000',
  },
});

const SeedscardDetails = ({ classes, t, account, onDetail, cardname }) => {
  const header = (
    <div className={classes.SeedsCard__header}>
      <img alt="interest account icon" className={classes.SeedsCard__icon} src={seedscardIcon} />
      <div className={classes.SeedsCard__title}>
        {t('common.widgetName', {
          widgetNamePlaceholder: cardname.replace(/^\w/, c => c.toUpperCase()),
        })}
      </div>
      <div className={classes.SeedsCard__value}>
        ...
        {account &&
          account.id &&
          account.accountNumber.substring(
            account.accountNumber.length - 4,
            account.accountNumber.length
          )}
      </div>
      <div className={classes.SeedsCard__action}>
        <i className="fas fa-ellipsis-v" />
      </div>
    </div>
  );

  return (
    // eslint-disable-next-line jsx-a11y/click-events-have-key-events,jsx-a11y/no-static-element-interactions
    <div
      onClick={account && account.id ? () => onDetail({ cardname, accountID: account.id }) : null}
    >
      <div className={classes.SeedsCard}>
        {account && account.id ? (
          <>
            {header}
            <p className={classes.SeedsCard__balance}>
              ${account.balance.toString().replace(/\B(?<!\.\d)(?=(\d{3})+(?!\d))/g, ',')}
            </p>
            <p className={classes.SeedsCard__balanceCaption}>Balance</p>
            {account.rewardPoints && (
              <p className={classes.SeedsCard__balanceReward}>
                Reward Points:{' '}
                <span className={classes.SeedsCard__balanceRewardValue}>
                  {account.rewardPoints}
                </span>
              </p>
            )}
          </>
        ) : (
          <>
            {header}
            <p className={classes.SeedsCard__balanceCaption}>
              You don&apos;t have a {cardname} account
            </p>
          </>
        )}
      </div>
    </div>
  );
};

SeedscardDetails.propTypes = {
  classes: PropTypes.shape({
    SeedsCard: PropTypes.string.isRequired,
    SeedsCard__header: PropTypes.string.isRequired,
    SeedsCard__icon: PropTypes.string.isRequired,
    SeedsCard__title: PropTypes.string.isRequired,
    SeedsCard__value: PropTypes.string.isRequired,
    SeedsCard__action: PropTypes.string.isRequired,
    SeedsCard__balance: PropTypes.string.isRequired,
    SeedsCard__balanceCaption: PropTypes.string.isRequired,
    SeedsCard__balanceReward: PropTypes.string.isRequired,
    SeedsCard__balanceRewardValue: PropTypes.string.isRequired,
  }).isRequired,
  account: seedscardType,
  t: PropTypes.func.isRequired,
  onDetail: PropTypes.func.isRequired,
  cardname: PropTypes.string.isRequired,
};

SeedscardDetails.defaultProps = {
  account: null,
};

export default compose(
  withStyles(styles, { withTheme: true }),
  withTranslation()
)(SeedscardDetails);
