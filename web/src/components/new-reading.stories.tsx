import React from 'react';
import { storiesOf } from '@storybook/react';
import NewReading from './new-reading';
import { action } from '@storybook/addon-actions';

storiesOf('Forms | New Reading', module)
  .addParameters({ jest: ['new-reading'] })
  .add('Default', () => (
    <NewReading
      initialValue="0"
      onClose={action('Cancel add')}
      onAdd={action('Adding reading')}
    />
  ));
