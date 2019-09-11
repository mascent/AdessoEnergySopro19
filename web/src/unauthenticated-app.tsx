import React from 'react';
import { Router, Redirect, RouteComponentProps } from '@reach/router';
import LoginScreen from './screens/login-screen';

const NotFound: React.FC<RouteComponentProps> = () => {
  return <Redirect to="/login" noThrow />;
};

const UnAuthenticatedApp: React.FC = () => {
  return (
    <Router>
      <LoginScreen path="/login" />

      <NotFound default />
    </Router>
  );
};

export default UnAuthenticatedApp;
