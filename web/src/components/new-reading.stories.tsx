import React from 'react';
import { storiesOf } from '@storybook/react';
import NewReading from './new-reading';

storiesOf('Forms | New Reading', module)
  .addParameters({ jest: ['new-reading.module'] })
  .add('Default', () => <NewReading onAdd={() => {}} />);
