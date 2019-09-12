import React from 'react';
import { storiesOf } from '@storybook/react';
import UserAppBar from './user-app-bar';
import AdminAppBar from './admin-app-bar';
import { AuthenticationProvider } from '../../providers/authentication-provider';
import { IssuesProvider } from '../../providers/issues-provider';

storiesOf('Form | Appbar', module)
  .add('User', () => (
    <IssuesProvider>
      <AuthenticationProvider>
        <UserAppBar />
      </AuthenticationProvider>
    </IssuesProvider>
  ))
  .add('Admin', () => (
    <AuthenticationProvider>
      <AdminAppBar selected="users" />
    </AuthenticationProvider>
  ));
