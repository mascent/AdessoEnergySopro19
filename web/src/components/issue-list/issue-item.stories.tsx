import React from 'react';
import { storiesOf } from '@storybook/react';
import IssueItem from './issue-item';
import { WithRouter } from '../../utils/with-router';

storiesOf('Generics | IssueItem', module).add('default', () => (
  <WithRouter>
    <IssueItem id="1" name="Test Test" subject="Das ist ein Ticket." />
  </WithRouter>
));
