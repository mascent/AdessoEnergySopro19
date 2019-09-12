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

  test('button forwards a given ref to the html button', () => {
    const ref = React.createRef<HTMLButtonElement>();
    const { getByText } = render(
      <PrimaryButton ref={ref} onClick={() => {}}>
        Test
      </PrimaryButton>
    );
    const button = getByText('Test');

    expect(ref.current).toBe(button);
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

  test('button forwards a given ref to the html button', () => {
    const ref = React.createRef<HTMLButtonElement>();
    const { getByText } = render(
      <SecondaryButton ref={ref} onClick={() => {}}>
        Test
      </SecondaryButton>
    );
    const button = getByText('Test');

    expect(ref.current).toBe(button);
  });
});
