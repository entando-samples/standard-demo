import React from 'react';
import { fireEvent, render, wait } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { apiUserGet, apiUserPut } from 'api/users';
import UserEditFormContainer from 'components/UserEditFormContainer';
import 'i18n/__mocks__/i18nMock';
import userMock from 'components/__mocks__/userMocks';

jest.mock('api/users');

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

describe('UserEditFormContainer', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  const errorMessageKey = 'error.dataLoading';
  const successMessageKey = 'common.dataSaved';

  const onErrorMock = jest.fn();
  const onUpdateMock = jest.fn();

  it('loads data', async () => {
    apiUserGet.mockImplementation(() => Promise.resolve(userMock));
    const { queryByText } = render(
      <UserEditFormContainer id="1" onError={onErrorMock} onUpdate={onUpdateMock} />
    );

    await wait(() => {
      expect(apiUserGet).toHaveBeenCalledTimes(1);
      expect(apiUserGet).toHaveBeenCalledWith('1');
      expect(queryByText(errorMessageKey)).not.toBeInTheDocument();
      expect(onErrorMock).toHaveBeenCalledTimes(0);
    });
  });

  it('saves data', async () => {
    apiUserGet.mockImplementation(() => Promise.resolve(userMock));
    apiUserPut.mockImplementation(() => Promise.resolve(userMock));

    const { findByTestId, queryByText } = render(
      <UserEditFormContainer id="1" onError={onErrorMock} onUpdate={onUpdateMock} />
    );

    const saveButton = await findByTestId('submit-btn');

    fireEvent.click(saveButton);

    await wait(() => {
      expect(apiUserPut).toHaveBeenCalledTimes(1);
      expect(apiUserPut).toHaveBeenCalledWith(userMock);
      expect(queryByText(successMessageKey)).toBeInTheDocument();
      expect(onErrorMock).toHaveBeenCalledTimes(0);
      expect(queryByText(errorMessageKey)).not.toBeInTheDocument();
    });
  });

  it('shows an error if data is not successfully loaded', async () => {
    apiUserGet.mockImplementation(() => Promise.reject());
    const { queryByText } = render(
      <UserEditFormContainer id="1" onError={onErrorMock} onUpdate={onUpdateMock} />
    );

    await wait(() => {
      expect(apiUserGet).toHaveBeenCalledTimes(1);
      expect(apiUserGet).toHaveBeenCalledWith('1');
      expect(onErrorMock).toHaveBeenCalledTimes(1);
      expect(queryByText(errorMessageKey)).toBeInTheDocument();
      expect(queryByText(successMessageKey)).not.toBeInTheDocument();
    });
  });

  it('shows an error if data is not successfully saved', async () => {
    apiUserGet.mockImplementation(() => Promise.resolve(userMock));
    apiUserPut.mockImplementation(() => Promise.reject());
    const { findByTestId, getByText } = render(
      <UserEditFormContainer id="1" onError={onErrorMock} />
    );

    const saveButton = await findByTestId('submit-btn');

    fireEvent.click(saveButton);

    await wait(() => {
      expect(apiUserGet).toHaveBeenCalledTimes(1);
      expect(apiUserGet).toHaveBeenCalledWith('1');

      expect(apiUserPut).toHaveBeenCalledTimes(1);
      expect(apiUserPut).toHaveBeenCalledWith(userMock);

      expect(onErrorMock).toHaveBeenCalledTimes(1);
      expect(getByText(errorMessageKey)).toBeInTheDocument();
    });
  });
});
