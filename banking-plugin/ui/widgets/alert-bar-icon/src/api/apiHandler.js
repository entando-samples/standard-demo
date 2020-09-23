import { apiStatementPut, apiStatementsGet } from 'api/statements/statements';
import { apiAlertsGet, apiAlertsPut } from 'api/alerts/alerts';

export const apiList = {
  viewStatements: 'viewstatements',
  manageAlerts: 'managealerts',
};

export const getApiContext = apiToCall => {
  let api;
  switch (apiToCall) {
    case apiList.viewStatements: {
      api = apiStatementsGet;
      break;
    }
    case apiList.manageAlerts: {
      api = apiAlertsGet;
      break;
    }
    default: {
      api = new Error(`Error! Unknown api :${apiToCall}`);
    }
  }
  return api;
};

export const putApiContext = apiToCall => {
  let api;
  switch (apiToCall) {
    case apiList.viewStatements: {
      api = apiStatementPut;
      break;
    }
    case apiList.manageAlerts: {
      api = apiAlertsPut;
      break;
    }
    default: {
      api = new Error(`Error! Unknown api :${apiToCall}`);
    }
  }
  return api;
};
