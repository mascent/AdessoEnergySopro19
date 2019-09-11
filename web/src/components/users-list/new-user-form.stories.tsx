import React from 'react';
import { storiesOf } from '@storybook/react';
import NewUserForm from './new-user-form';
import { action } from '@storybook/addon-actions';

storiesOf('Forms | New User', module)
  .addParameters({ jest: ['new-user-form'] })
  .add('Default', () => (
    <NewUserForm onCreate={action('Creating')} onCancel={action('Canceling')} />
  ));
