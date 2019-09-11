import React from 'react';
import styles from './admin-dashboard.module.scss';
import ContainerCard from './generics/container-card';
import UserList from './users-list/user-list';
import { useUsers } from '../providers/users-provider';

const AdminDashboard: React.FC = () => {
  const { users } = useUsers();

  return (
    <ContainerCard className={styles.container}>
      <UserList users={users} />
    </ContainerCard>
  );
};

export default AdminDashboard;
