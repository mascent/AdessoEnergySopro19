import React from 'react';
import { storiesOf } from '@storybook/react';
import UserItem from './user-item';
import { withKnobs } from '@storybook/addon-knobs';
import { WithRouter } from '../../utils/with-router';

storiesOf('Generic | UserItem', module)
  .addDecorator(withKnobs)
  .add('default', () => (
    <WithRouter>
      <UserItem
        id="1"
        firstName="Test"
        lastName="Test"
        kundenNummer="12345678"
      />
    </WithRouter>
  ));
