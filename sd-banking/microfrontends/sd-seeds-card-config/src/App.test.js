import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

test('renders all card choices', () => {
  const { getByText } = render(<App />);
  const checkingElement = getByText(/Checking/i);
  const creditCardElement = getByText(/Credit Card/i);
  const savingsElement = getByText(/Savings/i);
  expect(checkingElement).toBeInTheDocument();
  expect(creditCardElement).toBeInTheDocument();
  expect(savingsElement).toBeInTheDocument();
});
