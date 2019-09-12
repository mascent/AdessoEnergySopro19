import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import NewUserForm from './new-user-form';

test('New User informations are delivered', () => {
  const handler = jest.fn();

  const { getByLabelText, getByText } = render(
    <NewUserForm onCreate={handler} onCancel={handler} />
  );

  const kundennummer = getByLabelText('Kundennummer');
  const firstName = getByLabelText('Vorname');
  const lastName = getByLabelText('Nachname');
  const email = getByLabelText('Email');
  const password = getByLabelText('Passwort');
  const passwordValidate = getByLabelText('Passwort bestätigen');
  const button = getByText('Erstellen');

  fireEvent.change(firstName, { target: { value: 'Name' } });
  fireEvent.change(lastName, { target: { value: 'Nachname' } });
  fireEvent.change(email, { target: { value: 'name.name@name.de' } });
  fireEvent.change(kundennummer, { target: { value: '12345678' } });
  fireEvent.change(password, { target: { value: 'password' } });
  fireEvent.change(passwordValidate, { target: { value: 'password' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(1);
  expect(handler).toBeCalledWith(
    '12345678',
    'Name',
    'Nachname',
    'name.name@name.de',
    'password'
  );
});

test('Informations arent send when customerID is missing', () => {
  const handler = jest.fn();

  const { getByLabelText, getByText } = render(
    <NewUserForm onCreate={handler} onCancel={handler} />
  );

  const firstName = getByLabelText('Vorname');
  const email = getByLabelText('Email');
  const password = getByLabelText('Passwort');
  const passwordValidate = getByLabelText('Passwort bestätigen');
  const button = getByText('Erstellen');

  fireEvent.change(firstName, { target: { value: 'Name' } });
  fireEvent.change(email, { target: { value: 'name.name@name.de' } });
  fireEvent.change(password, { target: { value: 'password' } });
  fireEvent.change(passwordValidate, { target: { value: 'password' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(0);
});

test('Informations arent send when Name is missing', () => {
  const handler = jest.fn();

  const { getByLabelText, getByText } = render(
    <NewUserForm onCreate={handler} onCancel={handler} />
  );

  const kundennummer = getByLabelText('Kundennummer');
  const email = getByLabelText('Email');
  const password = getByLabelText('Passwort');
  const passwordValidate = getByLabelText('Passwort bestätigen');

  const button = getByText('Erstellen');

  fireEvent.change(kundennummer, { target: { value: '1234567890' } });
  fireEvent.change(email, { target: { value: 'name.name@name.de' } });
  fireEvent.change(password, { target: { value: 'password' } });
  fireEvent.change(passwordValidate, { target: { value: 'password' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(0);
});

test('Informations arent send when Email is missing', () => {
  const handler = jest.fn();

  const { getByLabelText, getByText } = render(
    <NewUserForm onCreate={handler} onCancel={handler} />
  );

  const kundennummer = getByLabelText('Kundennummer');
  const firstName = getByLabelText('Vorname');
  const password = getByLabelText('Passwort');
  const passwordValidate = getByLabelText('Passwort bestätigen');
  const button = getByText('Erstellen');

  fireEvent.change(firstName, { target: { value: 'Name' } });
  fireEvent.change(kundennummer, { target: { value: '1234567890' } });
  fireEvent.change(password, { target: { value: 'password' } });
  fireEvent.change(passwordValidate, { target: { value: 'password' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(0);
});

test('Informations arent send when Password is missing', () => {
  const handler = jest.fn();

  const { getByLabelText, getByText } = render(
    <NewUserForm onCreate={handler} onCancel={handler} />
  );

  const kundennummer = getByLabelText('Kundennummer');
  const firstName = getByLabelText('Vorname');
  const email = getByLabelText('Email');
  const passwordValidate = getByLabelText('Passwort bestätigen');
  const button = getByText('Erstellen');

  fireEvent.change(firstName, { target: { value: 'Name' } });
  fireEvent.change(kundennummer, { target: { value: '1234567890' } });
  fireEvent.change(email, { target: { value: 'asd.asd@asd.asd' } });
  fireEvent.change(passwordValidate, { target: { value: 'password' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(0);
});

test('Information arent send when Password and Password validation are different is missing', () => {
  const handler = jest.fn();

  const { getByLabelText, getByText } = render(
    <NewUserForm onCreate={handler} onCancel={handler} />
  );

  const kundennummer = getByLabelText('Kundennummer');
  const firstName = getByLabelText('Vorname');
  const email = getByLabelText('Email');
  const password = getByLabelText('Passwort');
  const passwordValidate = getByLabelText('Passwort bestätigen');
  const button = getByText('Erstellen');

  fireEvent.change(firstName, { target: { value: 'Name' } });
  fireEvent.change(kundennummer, { target: { value: '1234567890' } });
  fireEvent.change(email, { target: { value: 'asd.asd@asd.asd' } });
  fireEvent.change(password, { target: { value: 'nicht gleich password' } });
  fireEvent.change(passwordValidate, { target: { value: 'password' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(0);
});
