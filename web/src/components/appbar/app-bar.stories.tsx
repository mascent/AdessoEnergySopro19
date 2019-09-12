import React from 'react';
import { storiesOf } from '@storybook/react';
import UserAppBar from './user-app-bar';
import AdminAppBar from './admin-app-bar';

storiesOf('Form | Appbar', module)
  .add('User', () => <UserAppBar />)
  .add('Admin', () => <AdminAppBar selected="users" />);
