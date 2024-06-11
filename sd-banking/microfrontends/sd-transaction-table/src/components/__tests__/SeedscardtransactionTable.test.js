import React from 'react';
import '@testing-library/jest-dom/extend-expect';
import { fireEvent, render } from '@testing-library/react';
import 'i18n/__mocks__/i18nMock';
import seedscardtransactionMocks from 'components/__mocks__/seedscardtransactionMocks';
import SeedscardtransactionTable from 'components/SeedscardtransactionTable';

describe('SeedscardtransactionTable', () => {
  it('shows seedscardtransactions', () => {
    const { getByText } = render(<SeedscardtransactionTable items={seedscardtransactionMocks} />);
    expect(getByText('7/27/2003')).toBeInTheDocument();
    expect(getByText('6/30/2007')).toBeInTheDocument();
  });

  it('shows no seedscardtransactions message', () => {
    const { queryByText } = render(<SeedscardtransactionTable items={[]} />);
    expect(queryByText('7/27/2003')).not.toBeInTheDocument();
    expect(queryByText('6/30/2007')).not.toBeInTheDocument();

    expect(queryByText('entities.seedscardtransaction.noItems')).toBeInTheDocument();
  });

  it('calls onSelect when the user clicks a table row', () => {
    const onSelectMock = jest.fn();
    const { getByText } = render(
      <SeedscardtransactionTable items={seedscardtransactionMocks} onSelect={onSelectMock} />
    );
    fireEvent.click(getByText('7/27/2003'));
    expect(onSelectMock).toHaveBeenCalledTimes(1);
  });
});
