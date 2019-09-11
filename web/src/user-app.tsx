import React from 'react';
import { Router, Redirect, RouteComponentProps } from '@reach/router';
import UserScreen from './screens/user-screen';
import { MetersProvider } from './providers/meters-provider';

const NotFound: React.FC<RouteComponentProps> = () => {
  return <Redirect to="/counters" noThrow />;
};

const UserApp: React.FC = () => {
  return (
    <MetersProvider>
      <Router>
        <UserScreen path="/counters/*" />
        <NotFound default />
      </Router>
    </MetersProvider>
  );
};

export default UserApp;
