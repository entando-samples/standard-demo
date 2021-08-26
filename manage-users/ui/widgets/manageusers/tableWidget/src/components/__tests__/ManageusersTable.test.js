import React from 'react';
import '@testing-library/jest-dom/extend-expect';
import { fireEvent, render } from '@testing-library/react';
import 'i18n/__mocks__/i18nMock';
import manageusersMocks from 'components/__mocks__/manageusersMocks';
import ManageusersTable from 'components/ManageusersTable';

describe('ManageusersTable', () => {
  it('shows manageusers', () => {
    const { getByText } = render(<ManageusersTable items={manageusersMocks} />);
    expect(
      getByText(
        'Iste assumenda nesciunt eum. Qui fugit beatae non sit laboriosam eum eos. Minima quae natus voluptatem maxime inventore qui id ut qui. Vel harum ea omnis fugiat.'
      )
    ).toBeInTheDocument();
    expect(
      getByText(
        'Quia eius quisquam qui corporis non. Quis non nostrum velit exercitationem. Nihil consequatur iusto eum. Praesentium maiores officiis dolores harum neque sit. Maiores qui omnis possimus architecto atque vero ullam. Excepturi molestias est quas voluptatem a doloremque repellendus.'
      )
    ).toBeInTheDocument();
  });

  it('shows no manageusers message', () => {
    const { queryByText } = render(<ManageusersTable items={[]} />);
    expect(
      queryByText(
        'Iste assumenda nesciunt eum. Qui fugit beatae non sit laboriosam eum eos. Minima quae natus voluptatem maxime inventore qui id ut qui. Vel harum ea omnis fugiat.'
      )
    ).not.toBeInTheDocument();
    expect(
      queryByText(
        'Quia eius quisquam qui corporis non. Quis non nostrum velit exercitationem. Nihil consequatur iusto eum. Praesentium maiores officiis dolores harum neque sit. Maiores qui omnis possimus architecto atque vero ullam. Excepturi molestias est quas voluptatem a doloremque repellendus.'
      )
    ).not.toBeInTheDocument();

    expect(queryByText('entities.manageusers.noItems')).toBeInTheDocument();
  });

  it('calls onSelect when the user clicks a table row', () => {
    const onSelectMock = jest.fn();
    const { getByText } = render(
      <ManageusersTable items={manageusersMocks} onSelect={onSelectMock} />
    );
    fireEvent.click(
      getByText(
        'Iste assumenda nesciunt eum. Qui fugit beatae non sit laboriosam eum eos. Minima quae natus voluptatem maxime inventore qui id ut qui. Vel harum ea omnis fugiat.'
      )
    );
    expect(onSelectMock).toHaveBeenCalledTimes(1);
  });
});
