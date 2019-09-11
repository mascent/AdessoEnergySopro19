import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import Snackbar, { SnackbarType } from './snackbar';

test('it displays a given string', () => {
  const { getByText } = render(
    <Snackbar type="info" text="Hallo" onClose={() => {}} />
  );

  expect(getByText('Hallo')).toBeVisible();
});

const types: SnackbarType[] = ['warning', 'success', 'error', 'info'];
test.each(types)('snackbar type %s is set as data attribute', type => {
  const { getByTestId } = render(
    <Snackbar type={type} text="Hallo" onClose={() => {}} />
  );

  const snackbar = getByTestId('snackbar');
  expect(snackbar).toBeVisible();

  const attr = snackbar.attributes.getNamedItem('data-type');
  expect(attr && attr.value === type).toBeTruthy();
});

test('clicking on close triggers a callback', () => {
  const handler = jest.fn();
  const { getByTitle } = render(
    <Snackbar type="info" text="Hallo" onClose={handler} />
  );

  fireEvent.click(getByTitle('Close'));

  expect(handler).toBeCalledTimes(1);
});

test('an optional action can be provided', () => {
  const handler = jest.fn();
  const { getByText } = render(
    <Snackbar
      type="info"
      text="Hallo"
      onClose={() => {}}
      action={{ handler, text: 'Action' }}
    />
  );

  fireEvent.click(getByText('Action'));

  expect(handler).toBeCalledTimes(1);
});
