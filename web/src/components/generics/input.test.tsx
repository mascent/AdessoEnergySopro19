import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import Input from './input';

test('input fires OnChange when changed', () => {
  const handler = jest.fn();
  const { getByLabelText } = render(
    <Input id="test" label="TestLabel" type="text" onChange={handler} />
  );
  const input = getByLabelText('TestLabel');

  fireEvent.change(input, { target: { value: 'New Value' } });

  expect(handler).toBeCalledTimes(1);
  expect(handler).toBeCalledWith('New Value');
});

test('onBlur is called when input looses focus', () => {
  const handler = jest.fn();
  const { getByLabelText } = render(
    <Input
      id="test"
      label="TestLabel"
      type="text"
      onBlur={handler}
      value="Dummy"
    />
  );
  const input = getByLabelText('TestLabel');

  fireEvent.blur(input);

  expect(handler).toBeCalledTimes(1);
  expect(handler).toBeCalledWith('Dummy');
});

test('Show the provided error message', () => {
  const { getByText } = render(
    <Input id="test" label="TestLabel" type="text" error="test error message" />
  );

  getByText('test error message');
});
