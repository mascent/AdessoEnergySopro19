import React from 'react';
import { Router, Redirect } from '@reach/router';
import LoginScreen from './screens/login-screen';

const UnAuthenticatedApp: React.FC = () => {
  return (
    <Router>
      <LoginScreen path="/login" />

      <Redirect to="/login" />
    </Router>
  );
};

export default UnAuthenticatedApp;
