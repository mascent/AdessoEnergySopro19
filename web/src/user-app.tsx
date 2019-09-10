import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from 'react-router-dom';

import { SectionHeader } from './components/generics/text';
import Logo from './components/generics/logo';
import UserScreen from './screens/user-screen';

const TempGreeting = () => (
  <main
    style={{
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      height: '100vh',
      boxSizing: 'border-box',
      padding: '24px'
    }}
  >
    <div
      style={{
        maxWidth: '550px'
      }}
    >
      <SectionHeader>
        Hi. It's nice that you found your way to the future. The part your are
        currently looking at is still under active construction. Like an old man
        once said: "The future may never be finished". Why don't you grab
        yourself a drink and wait for us until we finish it?
      </SectionHeader>
    </div>
  </main>
);

const UserApp: React.FC = () => {
  return (
    <Router>
      <Switch>
        <Route path="/counters" component={UserScreen} />
        <Redirect to="/counters" />
      </Switch>
    </Router>
  );
};

export default UserApp;
