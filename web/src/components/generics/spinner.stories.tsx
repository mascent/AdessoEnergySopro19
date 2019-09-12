import React from 'react';
import { storiesOf } from '@storybook/react';
import {
  withKnobs,
  optionsKnob,
  OptionsKnobOptions
} from '@storybook/addon-knobs';
import Spinner from './spinner';

type SpinnerSize = 'small' | 'medium' | 'large';

const sizes: {
  [key: string]: SpinnerSize;
} = {
  Small: 'small',
  Medium: 'medium',
  Large: 'large'
};
const defaultSize: SpinnerSize = 'medium';
const optionsObj: OptionsKnobOptions = {
  display: 'inline-radio'
};

storiesOf('Generics | Spinner', module)
  .addDecorator(withKnobs)
  .add('default', () => (
    <Spinner
      size={optionsKnob<SpinnerSize>('Size', sizes, defaultSize, optionsObj)}
    />
  ));
