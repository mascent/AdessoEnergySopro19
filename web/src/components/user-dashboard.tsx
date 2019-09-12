import React from 'react';
import ContainerCard from './generics/container-card';
import styles from './user-dashboard.module.scss';
import { useMeters } from '../providers/meters-provider';
import Spinner from './generics/spinner';
import { useAuth } from '../providers/authentication-provider';
import MeterList from './meters-list/meter-list';
import { Router } from '@reach/router';
import { SelectMeter } from './dashboard-content/select-call';
import MeterInformation from './dashboard-content/meter-information';

const UserDashboard: React.FC = () => {
  const { userId } = useAuth();
  const { meters, isLoading } = useMeters(userId);

  if (isLoading)
    return (
      <ContainerCard className={styles.container}>
        <Spinner size="large" />
      </ContainerCard>
    );

  return (
    <ContainerCard className={styles.container}>
      <MeterList meters={meters} hideAdd={true} />
      <div className={styles.contentContainer}>
        <Router className={styles.router}>
          <SelectMeter path="/" />
          <MeterInformation path=":id" />
        </Router>
      </div>
    </ContainerCard>
  );
};

export default UserDashboard;
