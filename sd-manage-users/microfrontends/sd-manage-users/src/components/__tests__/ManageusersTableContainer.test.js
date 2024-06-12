import React from 'react';
import { render, wait } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

import manageusersMocks from 'components/__mocks__/manageusersMocks';
import { apiManageusersGet } from 'api/manageusers';
import 'i18n/__mocks__/i18nMock';
import ManageusersTableContainer from 'components/ManageusersTableContainer';

jest.mock('api/manageusers');

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

describe('ManageusersTableContainer', () => {
  const errorMessageKey = 'error.dataLoading';

  afterEach(() => {
    jest.clearAllMocks();
  });

  it('calls API', async () => {
    apiManageusersGet.mockImplementation(() =>
      Promise.resolve({ manageusers: manageusersMocks, count: 2 })
    );
    const { queryByText } = render(<ManageusersTableContainer />);

    await wait(() => {
      expect(apiManageusersGet).toHaveBeenCalledTimes(1);
      expect(queryByText(errorMessageKey)).not.toBeInTheDocument();
    });
  });

  it('shows an error if the API call is not successful', async () => {
    apiManageusersGet.mockImplementation(() => {
      throw new Error();
    });
    const { getByText } = render(<ManageusersTableContainer />);

    wait(() => {
      expect(apiManageusersGet).toHaveBeenCalledTimes(1);
      expect(getByText(errorMessageKey)).toBeInTheDocument();
    });
  });
});
