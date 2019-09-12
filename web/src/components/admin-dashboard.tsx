import React from 'react';
import styles from './admin-dashboard.module.scss';
import ContainerCard from './generics/container-card';
import UserList from './users-list/user-list';
import { useUsers } from '../providers/users-provider';
import { Router, navigate } from '@reach/router';
import MeterDisplayWithUser from './dashboard-content/meter-display-with-user';
import NewUserForm from './users-list/new-user-form';
import { SelectUser } from './dashboard-content/select-call';
import { useSnackBar } from '../providers/snackbar-provider';

const AdminDashboard: React.FC = () => {
  const { users, addUser } = useUsers();
  const showSnackbar = useSnackBar();

  function handleAddUser(
    customerNumber: string,
    firstName: string,
    lastName: string,
    password: string,
    email: string
  ) {
    addUser({ customerNumber, firstName, lastName, email, password }).then(
      success => {
        if (success) showSnackbar('success', 'Kunde erstellt');
        else showSnackbar('error', 'Kunde konnte nicht erstellt werden');
      }
    );
  }

  if (!users) return null;

  return (
    <ContainerCard className={styles.container}>
      <UserList users={users} />
      <Router className={styles.router} basepath="/admin/users">
        <SelectUser path="/" />
        <NewUserForm
          path="/new"
          onCreate={handleAddUser}
          onCancel={() => navigate('../')}
        />
        <MeterDisplayWithUser path="/:userId/*" />
      </Router>
    </ContainerCard>
  );
};

export default AdminDashboard;
