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

test('error attribute is false when no error is provided', () => {
  const { getByLabelText } = render(
    <Input id="test" label="TestLabel" type="text" />
  );

  const errorAttr = getByLabelText('TestLabel').attributes.getNamedItem(
    'data-error'
  );

  expect(errorAttr && errorAttr.value === 'false').toBeTruthy();
});

test('error attribute is false when error is null', () => {
  const { getByLabelText } = render(
    <Input id="test" label="TestLabel" type="text" error={null} />
  );

  const errorAttr = getByLabelText('TestLabel').attributes.getNamedItem(
    'data-error'
  );

  expect(errorAttr && errorAttr.value === 'false').toBeTruthy();
});

test('error attribute is false when error only contains spaces', () => {
  const { getByLabelText } = render(
    <Input id="test" label="TestLabel" type="text" error={'    '} />
  );

  const errorAttr = getByLabelText('TestLabel').attributes.getNamedItem(
    'data-error'
  );

  expect(errorAttr && errorAttr.value === 'false').toBeTruthy();
});

test('shows the provided error message and sets attribute on the input', () => {
  const { getByText, getByLabelText } = render(
    <Input id="test" label="TestLabel" type="text" error="test error message" />
  );

  const errorMsg = getByText('test error message');
  const errorAttr = getByLabelText('TestLabel').attributes.getNamedItem(
    'data-error'
  );

  expect(errorMsg).toBeVisible();
  expect(errorAttr && errorAttr.value === 'true').toBeTruthy();
});
