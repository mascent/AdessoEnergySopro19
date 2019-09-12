import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import NewReading from './new-reading';

test('New Reading gets added', () => {
  const handler = jest.fn();

  const { getByLabelText, getByText } = render(
    <NewReading initialValue="123" onClose={() => {}} onAdd={handler} />
  );

  const reading = getByLabelText('Stand');
  const button = getByText('Hinzufügen');

  fireEvent.change(reading, { target: { value: '584034' } });
  fireEvent.click(button);

  expect(handler).toBeCalledTimes(1);
});

test('New Reading doesnt get added, when Information are missing', () => {
  const handler = jest.fn();

  const { getByText } = render(
    <NewReading initialValue="123" onClose={() => {}} onAdd={handler} />
  );

  const button = getByText('Hinzufügen');
  fireEvent.click(button);
  expect(handler).toBeCalledTimes(0);
});
