import React from 'react';
import Logo, { LogoType } from './logo';
import { render } from '@testing-library/react';
import renderer from 'react-test-renderer';

const types: LogoType[] = [
  'no-text',
  'text-horizontal-stacked',
  'text-horizontal',
  'text-vertical',
  'with-bg'
];

test.each(types)('Logo of type %s matches with snapshot', type => {
  const { getByTestId } = render(<Logo type={type} />);
  const logo = getByTestId(type);

  expect(logo).toMatchSnapshot();
});
