import React from 'react';
import { RouteComponentProps, Redirect, Router } from '@reach/router';
import { MetersProvider } from './providers/meters-provider';
import { ReadingsProvider } from './providers/readings-provider';
import { UsersProvider } from './providers/users-provider';
import { IssuesProvider } from './providers/issues-provider';
import AdminScreen from './screens/admin-screen';

const NotFound: React.FC<RouteComponentProps> = () => {
  return <Redirect to="/dashboard/users" noThrow />;
};

const AdminApp: React.FC = () => {
  return (
    <UsersProvider>
      <IssuesProvider>
        <MetersProvider>
          <ReadingsProvider>
            <Router>
              <AdminScreen path="/dashboard/users/*" />
              <NotFound default />
            </Router>
          </ReadingsProvider>
        </MetersProvider>
      </IssuesProvider>
    </UsersProvider>
  );
};
export default AdminApp;
