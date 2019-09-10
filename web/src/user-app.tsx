import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from 'react-router-dom';
import UserScreen from './screens/user-screen';
import { MetersProvider } from './providers/meters-provider';

const UserApp: React.FC = () => {
  return (
    <MetersProvider>
      <Router>
        <Switch>
          <Route path="/counters" component={UserScreen} />
          <Redirect to="/counters" />
        </Switch>
      </Router>
    </MetersProvider>
  );
};

export default UserApp;
