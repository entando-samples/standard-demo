import React from 'react';
import { render, wait } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

import 'components/__mocks__/i18n';
import getSeedscard from 'api/seedscard';
import seedscardApiGetResponseMock from 'components/__mocks__/seedscardMocks';
import CardDetailsContainer from 'components/SeedscardDetailsContainer';

jest.mock('api/seedscard');

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

beforeEach(() => {
  getSeedscard.mockClear();
});

describe('SeedscardDetailsContainer component', () => {
  test('requests data when component is mounted', async () => {
    getSeedscard.mockImplementation(() => Promise.resolve(seedscardApiGetResponseMock));

    render(<CardDetailsContainer id="1" />);

    await wait(() => {
      expect(getSeedscard).toHaveBeenCalledTimes(1);
    });
  });

  test('data is shown after mount API call', async () => {
    getSeedscard.mockImplementation(() => Promise.resolve(seedscardApiGetResponseMock));

    // const { getByText } = render(<SeedscardDetailsContainer id="1" />);

    await wait(() => {
      expect(getSeedscard).toHaveBeenCalledTimes(1);
      // expect(getByText('entities.seedscard.accountNumber')).toBeInTheDocument();
      // expect(getByText('entities.seedscard.balance')).toBeInTheDocument();
      // expect(getByText('entities.seedscard.userId')).toBeInTheDocument();
    });
  });

  // test('error is shown after failed API call', async () => {
  //   const onErrorMock = jest.fn();
  //   getSeedscard.mockImplementation(() => Promise.reject());
  //
  //   const { getByText } = render(<SeedscardDetailsContainer id="1" onError={onErrorMock} />);
  //
  //   await wait(() => {
  //     expect(getSeedscard).toHaveBeenCalledTimes(1);
  //     expect(onErrorMock).toHaveBeenCalledTimes(1);
  //     expect(getByText('common.couldNotFetchData')).toBeInTheDocument();
  //   });
  // });
});
