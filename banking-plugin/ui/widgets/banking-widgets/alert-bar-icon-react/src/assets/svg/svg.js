import investments from 'assets/svg/investments.svg';
import managealerts from 'assets/svg/managealerts.svg';
import paybills from 'assets/svg/paybills.svg';
import sendmoney from 'assets/svg/sendmoney.svg';
import transfermoney from 'assets/svg/transfermoney.svg';
import viewstatements from 'assets/svg/viewstatements.svg';

const svgTypes = {
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
