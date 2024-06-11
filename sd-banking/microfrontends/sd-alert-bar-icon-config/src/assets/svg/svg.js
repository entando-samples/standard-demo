import investments from './investments.svg';
import managealerts from './managealerts.svg';
import paybills from './paybills.svg';
import sendmoney from './sendmoney.svg';
import transfermoney from './transfermoney.svg';
import viewstatements from './viewstatements.svg';

export const svgTypes = {
  investments: 'investments',
  managealerts: 'managealerts',
  paybills: 'paybills',
  sendmoney: 'sendmoney',
  transfermoney: 'transfermoney',
  viewstatements: 'viewstatements',
};

const svg = {
  investments,
  managealerts,
  paybills,
  sendmoney,
  transfermoney,
  viewstatements,
};

// eslint-disable-next-line import/prefer-default-export
export const getSvg = icon => {
  switch (icon) {
    case svgTypes.investments:
      return svg.investments;
    case svgTypes.managealerts:
      return svg.managealerts;
    case svgTypes.paybills:
      return svg.paybills;
    case svgTypes.sendmoney:
      return svg.sendmoney;
    case svgTypes.transfermoney:
      return svg.transfermoney;
    case svgTypes.viewstatements:
      return svg.viewstatements;
    default:
      return null;
  }
};
