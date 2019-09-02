import React from 'react';
import Logo, { LogoType } from './logo';
import { render } from '@testing-library/react';

const types: LogoType[] = [
  'no-text',
  'test-horizontal-stacked',
  'text-horizontal',
  'text-vertical',
  'with-bg'
];

test.each(types)('Logo of type %s matches with snapshot', type => {
  const { debug } = render(<Logo type={type} />);

  debug();
});
