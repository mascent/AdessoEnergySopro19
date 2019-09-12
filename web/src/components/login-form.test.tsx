import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import LoginForm from './login-form';

test('Login get Called with username and password', () => {
  const handler = jest.fn();
  const { getByLabelText, getByText } = render(<LoginForm onLogin={handler} />);
  const username = getByLabelText('Kundennummer');
  const password = getByLabelText('Passwort');
  const button = getByText('Login');

  fireEvent.change(username, { target: { value: 'New Value' } });
  fireEvent.change(password, { target: { value: 'password' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(1);
});

test('Login dont get called, when username is missing', () => {
  const handler = jest.fn();
  const { getByLabelText, getByText } = render(<LoginForm onLogin={handler} />);
  const password = getByLabelText('Passwort');
  const button = getByText('Login');

  fireEvent.change(password, { target: { value: 'password' } });

  fireEvent.click(button);

  const errorMessage = getByText('Keine valide Kundennummer');
  expect(handler).toBeCalledTimes(0);
  expect(errorMessage).toBeVisible();
});

test('Login dont get called, when password is missing', () => {
  const handler = jest.fn();
  const { getByLabelText, getByText } = render(<LoginForm onLogin={handler} />);
  const username = getByLabelText('Benutzername');
  const button = getByText('Login');

  fireEvent.change(username, { target: { value: 'password' } });

  fireEvent.click(button);
  const errorMessage = getByText('Kein valides Passwort');

  expect(handler).toBeCalledTimes(0);
  expect(errorMessage).toBeVisible();
});

test('Login dont get called, when username and password are missing', () => {
  const handler = jest.fn();
  const { getByText } = render(<LoginForm onLogin={handler} />);
  const button = getByText('Login');

  fireEvent.click(button);
  const errorNoPassword = getByText('Kein valides Passwort');
  const errorNoUsername = getByText('Keine valide Kundennummer');

  expect(handler).toBeCalledTimes(0);
  expect(errorNoPassword).toBeVisible();
  expect(errorNoUsername).toBeVisible();
});
