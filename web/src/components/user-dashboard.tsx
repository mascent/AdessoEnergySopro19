import React from 'react';
import ContainerCard from './generics/container-card';
import styles from './user-dashboard.module.scss';
import { useMeters } from '../providers/meters-provider';
import Spinner from './generics/spinner';
import { useAuth } from '../providers/authentication-provider';
import MeterDisplay from './dashboard-content/meter-display';

const UserDashboard: React.FC = () => {
  const { userId } = useAuth();
  const { isLoading } = useMeters(userId);

  if (isLoading)
    return (
      <ContainerCard className={styles.container}>
        <Spinner />
      </ContainerCard>
    );

  return (
    <ContainerCard className={styles.container}>
      <MeterDisplay userId={userId} />
    </ContainerCard>
  );
};

export default UserDashboard;
