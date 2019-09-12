import React from 'react';
import { storiesOf } from '@storybook/react';
import UserList from './user-list';
import { buildUser } from '../../utils/fake-builder';
import { User } from '../../typings/provider-data-interfaces';
import { WithRouter } from '../../utils/with-router';

const users: User[] = [
  buildUser(),
  buildUser(),
  buildUser(),
  buildUser(),
  buildUser(),
  buildUser(),
  buildUser(),
  buildUser(),
  buildUser(),
  buildUser()
];

storiesOf('Dashboard Content | UserList', module)
  .add('empty', () => (
    <WithRouter>
      <UserList users={[]} />
    </WithRouter>
  ))
  .add('filled', () => (
    <WithRouter>
      <div style={{ height: '70vh' }}>
        <UserList users={users} />
      </div>
    </WithRouter>
  ));
