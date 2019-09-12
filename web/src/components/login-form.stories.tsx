import React from 'react';
import { storiesOf } from '@storybook/react';
import LoginForm from './login-form';
import { action } from '@storybook/addon-actions';

storiesOf('Screens | Login', module)
  .addParameters({ jest: ['login-form'] })
  .add('Default', () => <LoginForm onLogin={action('Logging in')} />);
