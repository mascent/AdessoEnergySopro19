import React from 'react';
import Illustration, { IllustrationType } from './illustrations';
import { render } from '@testing-library/react';

const types: IllustrationType[] = ['Auth', 'NoSelected'];

test.each(types)('Illustration of type %s matches with snapshot', type => {
  const { getByTestId } = render(<Illustration type={type} />);
  const logo = getByTestId(type);
  expect(logo).toMatchSnapshot();
});
