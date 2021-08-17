import React from 'react';
import { render, wait } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

import seedscardtransactionMocks from 'components/__mocks__/seedscardtransactionMocks';
import { apiSeedscardtransactionsGet } from 'api/seedscardtransactions';
import 'i18n/__mocks__/i18nMock';
import SeedscardtransactionTableContainer from 'components/SeedscardtransactionTableContainer';

jest.mock('api/seedscardtransactions');

jest.mock('auth/KeycloakContext', () => {
  const withKeycloak = Component => {
    return props => (
      <Component
        {...props} // eslint-disable-line react/jsx-props-no-spreading
        keycloak={{
          initialized: true,
          authenticated: true,
        }}
      />
    );
  };

  return { withKeycloak };
});

describe('SeedscardtransactionTableContainer', () => {
  const errorMessageKey = 'error.dataLoading';

  afterEach(() => {
    jest.clearAllMocks();
  });

  it('calls API', async () => {
    apiSeedscardtransactionsGet.mockImplementation(() =>
      Promise.resolve({ seedscardtransactions: seedscardtransactionMocks, count: 2 })
    );
    const { queryByText } = render(<SeedscardtransactionTableContainer />);

    await wait(() => {
      expect(apiSeedscardtransactionsGet).toHaveBeenCalledTimes(1);
      expect(queryByText(errorMessageKey)).not.toBeInTheDocument();
    });
  });

  it('shows an error if the API call is not successful', async () => {
    apiSeedscardtransactionsGet.mockImplementation(() => {
      throw new Error();
    });
    const { getByText } = render(<SeedscardtransactionTableContainer />);

    wait(() => {
      expect(apiSeedscardtransactionsGet).toHaveBeenCalledTimes(1);
      expect(getByText(errorMessageKey)).toBeInTheDocument();
    });
  });
});
