import React from 'react';
import '@testing-library/jest-dom/extend-expect';
import { fireEvent, render } from '@testing-library/react';
import 'i18n/__mocks__/i18nMock';
import seedscardtransactionMocks from 'components/__mocks__/seedscardtransactionMocks';
import SeedscardtransactionTable from 'components/SeedscardtransactionTable';

describe('SeedscardtransactionTable', () => {
  it('shows seedscardtransactions', () => {
    const { getByText } = render(<SeedscardtransactionTable items={seedscardtransactionMocks} />);
    expect(getByText('2003-07-27')).toBeInTheDocument();
    expect(getByText('2007-06-30')).toBeInTheDocument();
  });

  it('shows no seedscardtransactions message', () => {
    const { queryByText } = render(<SeedscardtransactionTable items={[]} />);
    expect(queryByText('2003-07-27')).not.toBeInTheDocument();
    expect(queryByText('2007-06-30')).not.toBeInTheDocument();

    expect(queryByText('entities.seedscardtransaction.noItems')).toBeInTheDocument();
  });

  it('calls onSelect when the user clicks a table row', () => {
    const onSelectMock = jest.fn();
    const { getByText } = render(
      <SeedscardtransactionTable items={seedscardtransactionMocks} onSelect={onSelectMock} />
    );
    fireEvent.click(getByText('2003-07-27'));
    expect(onSelectMock).toHaveBeenCalledTimes(1);
  });
});
