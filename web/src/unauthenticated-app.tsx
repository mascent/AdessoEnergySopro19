import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from 'react-router-dom';
import LoginScreen from './screens/login-screen';

const UnAuthenticatedApp: React.FC = () => {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={LoginScreen} />
        <Redirect to="/login" />
      </Switch>
    </Router>
  );
};

export default UnAuthenticatedApp;
