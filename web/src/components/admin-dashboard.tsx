import React from 'react';
import styles from './admin-dashboard.module.scss';
import ContainerCard from './generics/container-card';
import UserList from './users-list/user-list';
import { useUsers } from '../providers/users-provider';
import { Router, RouteComponentProps, navigate } from '@reach/router';
import MeterList from './meters-list/meter-list';
import { useMeters } from '../providers/meters-provider';
import MeterDisplayWithUser from './dashboard-content/meter-display-with-user';
import NewUserForm from './users-list/new-user-form';

const AdminDashboard: React.FC = () => {
  const { users } = useUsers();

  return (
    <ContainerCard className={styles.container}>
      <UserList users={users} />
      <Router className={styles.router} basepath="/admin/users">
        <NewUserForm
          path="/new"
          onCreate={() => {}}
          onCancel={() => navigate('../')}
        />
        <MeterDisplayWithUser path="/:userId" />
      </Router>
    </ContainerCard>
  );
};

export default AdminDashboard;
