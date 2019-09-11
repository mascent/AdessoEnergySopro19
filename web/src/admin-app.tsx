import React from 'react';
import { RouteComponentProps, Redirect, Router } from '@reach/router';
import { MetersProvider } from './providers/meters-provider';
import { ReadingsProvider } from './providers/readings-provider';
import { UsersProvider } from './providers/users-provider';
import { IssuesProvider } from './providers/issues-provider';
import { AdminUsersScreen, AdminIssuesScreen } from './screens/admin-screen';
import IssueInformation from './components/dashboard-content/issue-information';

const NotFound: React.FC<RouteComponentProps> = () => {
  return <Redirect to="/admin/users" noThrow />;
};

const AdminApp: React.FC = () => {
  return (
    <UsersProvider>
      <IssuesProvider>
        <MetersProvider>
          <ReadingsProvider>
            <Router>
              <AdminUsersScreen path="/admin/users/*" />
              <AdminIssuesScreen path="/admin/issues/" />
              <NotFound default />
            </Router>
          </ReadingsProvider>
        </MetersProvider>
      </IssuesProvider>
    </UsersProvider>
  );
};
export default AdminApp;
