import React from 'react';
import Logo, { IllustrationType } from './illustrations';
import { render } from '@testing-library/react';

const types: IllustrationType[] = ['Auth', 'NoSelected'];

test.each(types)('Logo of type %s matches with snapshot', type => {
  const { getByTestId } = render(<Logo type={type} />);
  const logo = getByTestId(type);
  expect(logo).toMatchSnapshot();
});
