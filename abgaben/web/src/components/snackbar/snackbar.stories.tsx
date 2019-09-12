import React from 'react';
import { storiesOf } from '@storybook/react';
import {
  withKnobs,
  text,
  OptionsKnobOptions,
  optionsKnob
} from '@storybook/addon-knobs';
import Snackbar from './snackbar';
import { action } from '@storybook/addon-actions';

type SnackbarTypes = 'warning' | 'success' | 'error' | 'info';

const types: {
  [key: string]: SnackbarTypes;
} = {
  Warning: 'warning',
  Success: 'success',
  Error: 'error',
  Info: 'info'
};
const defaultType: SnackbarTypes = 'success';
const optionsObj: OptionsKnobOptions = {
  display: 'inline-radio'
};

storiesOf('Generics | Snackbar', module)
  .addDecorator(withKnobs)
  .add('default', () => (
    <Snackbar
      text={text('Message', 'Snackbar text')}
      type={optionsKnob<SnackbarTypes>('Type', types, defaultType, optionsObj)}
      onClose={action('Closing snackbar')}
    />
  ));
