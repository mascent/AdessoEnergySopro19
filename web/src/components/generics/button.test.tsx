import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import { PrimaryButton, SecondaryButton } from './button';

describe('PrimaryButton', () => {
  test('button calls onClick when clicked', () => {
    const handler = jest.fn();
    const { getByText } = render(
      <PrimaryButton onClick={handler}>PrimaryButton</PrimaryButton>
    );
    const button = getByText('PrimaryButton');
    fireEvent.click(button);
    expect(handler).toBeCalledTimes(1);
  });

  test('button does not call onClick when disabled', () => {
    const handler = jest.fn();
    const { getByText } = render(
      <PrimaryButton disabled onClick={handler}>
        PrimaryButton
      </PrimaryButton>
    );
    const button = getByText('PrimaryButton');
    fireEvent.click(button);
    expect(handler).toBeCalledTimes(0);
  });
});

describe('Secondary', () => {
  test('button calls onClick when clicked', () => {
    const handler = jest.fn();
    const { getByText } = render(
      <SecondaryButton onClick={handler}>SecondaryButton</SecondaryButton>
    );
    const button = getByText('SecondaryButton');
    fireEvent.click(button);
    expect(handler).toBeCalledTimes(1);
  });

  test('button does not call onClick when disabled', () => {
    const handler = jest.fn();
    const { getByText } = render(
      <SecondaryButton disabled onClick={handler}>
        SecondaryButton
      </SecondaryButton>
    );
    const button = getByText('SecondaryButton');
    fireEvent.click(button);
    expect(handler).toBeCalledTimes(0);
  });
});
