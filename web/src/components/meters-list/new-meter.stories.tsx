import React from 'react';
import { storiesOf } from '@storybook/react';
import NewMeter from './new-meter';
import { action } from '@storybook/addon-actions';

storiesOf('Forms | NewMeter', module).add('Default', () => (
  <NewMeter
    onCancel={action('Canceling')}
    onCreate={action('Creating new meter')}
  />
));
