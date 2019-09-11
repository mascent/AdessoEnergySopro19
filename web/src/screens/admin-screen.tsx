import React from 'react';
import styles from './admin-screen.module.scss';
import useDocumentTitle from '../hooks/use-document-title';
import UserDashboard from '../components/user-dashboard';
import AdminAppBar from '../components/appbar/admin-app-bar';
import { RouteComponentProps } from '@reach/router';
import AdminDashboard from './admin-dashboard';

const AdminScreen: React.FC<RouteComponentProps> = () => {
  useDocumentTitle('Admin Dashboard');

  return (
    <main className={styles.main}>
      <AdminAppBar />
      <div className={styles.container}>
        <AdminDashboard />
      </div>
    </main>
  );
};

export default AdminScreen;
