import { render, screen } from '@testing-library/react';
import MainPage from './mainPage/MainPage';

test('renders learn react link', () => {
  render(<MainPage />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
