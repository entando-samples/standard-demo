import React from 'react';
import { fireEvent, render, wait } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { apiUserPost } from 'api/users';
import UserAddFormContainer from 'components/UserAddFormContainer';
import 'i18n/__mocks__/i18nMock';
import userMock from 'components/__mocks__/userMocks';

jest.mock('api/users');
jest.mock('@material-ui/pickers', () => ({
  ...jest.requireActual('@material-ui/pickers'),
  // eslint-disable-next-line react/prop-types
  DateTimePicker: ({ id, value, name, label, onChange }) => {
    const handleChange = event => onChange(event.currentTarget.value);
    return (
      <span>
        <label htmlFor={id}>{label}</label>
        <input id={id} name={name} value={value || ''} onChange={handleChange} />
      </span>
    );
  },
}));

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

describe('UserAddFormContainer', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  const errorMessageKey = 'error.dataLoading';
  const successMessageKey = 'common.dataSaved';

  const onErrorMock = jest.fn();
  const onCreateMock = jest.fn();

  it('saves data', async () => {
    apiUserPost.mockImplementation(data => Promise.resolve(data));

    const { findByTestId, findByLabelText, queryByText, rerender } = render(
      <UserAddFormContainer onError={onErrorMock} onUpdate={onCreateMock} />
    );

    const usernameField = await findByLabelText('entities.user.username');
    fireEvent.change(usernameField, { target: { value: userMock.username } });

    const passwordField = await findByLabelText('entities.user.password');
    fireEvent.change(passwordField, { target: { value: userMock.password } });

    const emailField = await findByLabelText('entities.user.email');
    fireEvent.change(emailField, { target: { value: userMock.email } });

    rerender(<UserAddFormContainer onError={onErrorMock} onUpdate={onCreateMock} />);

    const saveButton = await findByTestId('submit-btn');

    fireEvent.click(saveButton);

    await wait(() => {
      expect(apiUserPost).toHaveBeenCalledTimes(1);
      expect(apiUserPost).toHaveBeenCalledWith(userMock);

      expect(queryByText(successMessageKey)).toBeInTheDocument();

      expect(onErrorMock).toHaveBeenCalledTimes(0);
      expect(queryByText(errorMessageKey)).not.toBeInTheDocument();
    });
  }, 7000);

  it('shows an error if data is not successfully saved', async () => {
    apiUserPost.mockImplementation(() => Promise.reject());

    const { findByTestId, findByLabelText, queryByText, rerender } = render(
      <UserAddFormContainer onError={onErrorMock} onUpdate={onCreateMock} />
    );

    const usernameField = await findByLabelText('entities.user.username');
    fireEvent.change(usernameField, { target: { value: userMock.username } });

    const passwordField = await findByLabelText('entities.user.password');
    fireEvent.change(passwordField, { target: { value: userMock.password } });

    const emailField = await findByLabelText('entities.user.email');
    fireEvent.change(emailField, { target: { value: userMock.email } });

    rerender(<UserAddFormContainer onError={onErrorMock} onUpdate={onCreateMock} />);

    const saveButton = await findByTestId('submit-btn');

    fireEvent.click(saveButton);

    await wait(() => {
      expect(apiUserPost).toHaveBeenCalledTimes(1);
      expect(apiUserPost).toHaveBeenCalledWith(userMock);

      expect(queryByText(successMessageKey)).not.toBeInTheDocument();

      expect(onErrorMock).toHaveBeenCalledTimes(1);
      expect(queryByText(errorMessageKey)).toBeInTheDocument();
    });
  }, 7000);
});
