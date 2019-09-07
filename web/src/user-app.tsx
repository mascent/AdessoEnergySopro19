import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from 'react-router-dom';

import { SectionHeader } from './components/generics/text';

const TempGreeting = () => (
  <main
    style={{
      display: 'flex',
      alignContent: 'center',
      justifyContent: 'center'
    }}
  >
    <SectionHeader>
      Hi. It's nice that you found your way to the future. The part your are
      currently looking at is still under active construction. Like a old man
      once said: "The future is never finished". Way don't you grab yourself a
      drink and wait for us to finish it?
    </SectionHeader>
  </main>
);

const UserApp: React.FC = () => {
  return (
    <Router>
      <Switch>
        <Route path="/dashboard" component={TempGreeting} />
        <Redirect to="/dashboard" />
      </Switch>
    </Router>
  );
};

export default UserApp;
