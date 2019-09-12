import React from 'react';
import styles from './admin-screen.module.scss';
import useDocumentTitle from '../hooks/use-document-title';
import AdminAppBar from '../components/appbar/admin-app-bar';
import { RouteComponentProps } from '@reach/router';
import AdminDashboard from '../components/admin-dashboard';
import IssuesDashboard from '../components/issues-dashboard';

const AdminUsersScreen: React.FC<RouteComponentProps> = () => {
  useDocumentTitle('Admin Users');

  return (
    <main className={styles.main}>
      <AdminAppBar selected="users" />
      <div className={styles.container}>
        <AdminDashboard />
      </div>
    </main>
  );
};

const AdminIssuesScreen: React.FC<RouteComponentProps> = () => {
  useDocumentTitle('Admin Issues');

  return (
    <main className={styles.main}>
      <AdminAppBar selected="tickets" />
      <div className={styles.container}>
        <IssuesDashboard />
      </div>
    </main>
  );
};

export { AdminUsersScreen, AdminIssuesScreen };
