import React from 'react';
import '@testing-library/jest-dom/extend-expect';
import { fireEvent, render, wait } from '@testing-library/react';
import 'i18n/__mocks__/i18nMock';
import userMock from 'components/__mocks__/userMocks';
import UserForm from 'components/UserForm';
import { createMuiTheme } from '@material-ui/core';
import { ThemeProvider } from '@material-ui/styles';

const theme = createMuiTheme();

describe('User Form', () => {
  it('shows form', () => {
    const { getByLabelText } = render(
      <ThemeProvider theme={theme}>
        <UserForm user={userMock} />
      </ThemeProvider>
    );
    expect(getByLabelText('entities.user.username').value).toBe('test');
  });

  it('submits form', async () => {
    const handleSubmit = jest.fn();
    const { getByTestId } = render(
      <ThemeProvider theme={theme}>
        <UserForm user={userMock} onSubmit={handleSubmit} />
      </ThemeProvider>
    );

    const form = getByTestId('user-form');
    fireEvent.submit(form);

    await wait(() => {
      expect(handleSubmit).toHaveBeenCalledTimes(1);
    });
  });
});
