import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import CustomerInformation from './customer-information';

test('New Customer Information get saved', () => {
  const handler = jest.fn();
  const { getByTitle, getByLabelText, getByText } = render(
    <CustomerInformation
      userInfo={{
        customerId: '123',
        email: 'a@b.de',
        firstName: 'Peter',
        lastName: 'Hans'
      }}
      onSave={handler}
    />
  );

  // Open dialog
  fireEvent.click(getByTitle('Informationen bearbeiten'));

  const surname = getByLabelText('Vorname');
  const lastname = getByLabelText('Nachname');
  const email = getByLabelText('Email');

  const button = getByText('Speichern');

  fireEvent.change(surname, { target: { value: 'Vorname' } });
  fireEvent.change(lastname, { target: { value: 'Nachname' } });
  fireEvent.change(email, { target: { value: 'asd.asd@asd.asd' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(1);
});

test('New Customer Information get saved', () => {
  const handler = jest.fn();
  const { getByTitle, getByLabelText, getByText } = render(
    <CustomerInformation
      userInfo={{
        customerId: '123',
        email: 'a@b.de',
        firstName: 'Peter',
        lastName: 'Hans'
      }}
      onSave={handler}
    />
  );

  // Open dialog
  fireEvent.click(getByTitle('Informationen bearbeiten'));

  const surname = getByLabelText('Vorname');
  const lastname = getByLabelText('Nachname');
  const email = getByLabelText('Email');

  const button = getByText('Speichern');

  fireEvent.change(surname, { target: { value: 'Vorname' } });
  fireEvent.change(lastname, { target: { value: 'Nachname' } });
  fireEvent.change(email, { target: { value: 'asd.asd@asd.asd' } });

  fireEvent.click(button);

  expect(handler).toBeCalledWith('Vorname', 'Nachname', 'asd.asd@asd.asd');
});
