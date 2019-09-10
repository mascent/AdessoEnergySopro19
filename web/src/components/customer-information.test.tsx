import React from 'react';
import { render, fireEvent, getByText } from '@testing-library/react';
import CustomerInformation from './customer-information';

test.skip('New Customer Information get saved', () => {
  const handler = jest.fn();
  const { getByLabelText, getByText } = render(
    <CustomerInformation onSave={handler} />
  );

  const customerID = getByLabelText('Kundennummer');
  const surname = getByLabelText('Vorname');
  const lastname = getByLabelText('Nachname');
  const email = getByLabelText('Email');

  const button = getByText('Speichern');

  fireEvent.change(customerID, { target: { value: '1234567890' } });
  fireEvent.change(surname, { target: { value: 'Vorname' } });
  fireEvent.change(lastname, { target: { value: 'Nachname' } });
  fireEvent.change(email, { target: { value: 'asd.asd@asd.asd' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(1);
});

test.skip('New Customer Information get saved', () => {
  const handler = jest.fn();
  const { getByLabelText, getByText } = render(
    <CustomerInformation onSave={handler} />
  );

  const customerID = getByLabelText('Kundennummer');
  const surname = getByLabelText('Vorname');
  const lastname = getByLabelText('Nachname');
  const email = getByLabelText('Email');

  const button = getByText('Speichern');

  fireEvent.change(customerID, { target: { value: '1234567890' } });
  fireEvent.change(surname, { target: { value: 'Vorname' } });
  fireEvent.change(lastname, { target: { value: 'Nachname' } });
  fireEvent.change(email, { target: { value: 'asd.asd@asd.asd' } });

  fireEvent.click(button);

  expect(handler).toBeCalledWith(
    '1234567890',
    'Vorname',
    'Nachname',
    'asd.asd@asd.asd'
  );
});
