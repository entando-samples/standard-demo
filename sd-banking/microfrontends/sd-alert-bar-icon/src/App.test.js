import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

test('renders list item element', () => {
  const { container } = render(
    <App
      modalOpen={false}
      toggleModal={() => {}}
      putDocumentRead={() => {}}
      onChangeDescription={() => {}}
      onChangeRangeFilter={() => {}}
    />
  );
  const classItem = container.getElementsByClassName('list-item');
  expect(classItem.length).toBe(1);
});
