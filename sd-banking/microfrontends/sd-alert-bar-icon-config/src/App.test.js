import { render, screen } from '@testing-library/react';
import App from './App';

test('renders Icon element', () => {
  render(<App />);
  const element = screen.getByText('Icon');
  expect(element).toBeInTheDocument();
});
