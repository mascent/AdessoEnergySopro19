import React from 'react';
import styles from './user-screen.module.scss';
import useDocumentTitle from '../hooks/use-document-title';
import UserDashboard from '../components/user-dashboard';
import UserAppBar from '../components/appbar/user-app-bar';
import { RouteComponentProps } from '@reach/router';

const UserScreen: React.FC<RouteComponentProps> = () => {
  useDocumentTitle('Dashboard');

  return (
    <main className={styles.main}>
      <UserAppBar />
      <UserDashboard />
    </main>
  );
};

export default UserScreen;
