import React from 'react';
import ContainerCard from './generics/container-card';
import styles from './user-dashboard.module.scss';
import { SelectMeter } from './dashboard-content/select-call';
import MeterList from './meters-list/meter-list';
import { useMeters } from '../providers/meters-provider';
import Spinner from './generics/spinner';
import { useAuth } from '../providers/authentication-provider';
import NewMeter from './meters-list/new-meter';
import { MeterType } from '../typings/provider-data-interfaces';
import { Router, navigate } from '@reach/router';
import MeterInformation from './dashboard-content/meter-information';

const UserDashboard: React.FC = () => {
  const { userId } = useAuth();
  const { meters, isLoading, addMeter } = useMeters(userId);

  if (isLoading)
    return (
      <ContainerCard className={styles.container}>
        <Spinner />
      </ContainerCard>
    );

  function createMeter(
    meterType: MeterType,
    name: string,
    meterNumber: string,
    initialValue: string
  ): void {
    addMeter({
      type: meterType,
      name,
      meterNumber
    });
  }

  return (
    <ContainerCard className={styles.container}>
      <MeterList meters={meters} />
      <div className={styles.contentContainer}>
        <Router className={styles.router}>
          <SelectMeter path="/" />
          <NewMeter
            path="/new"
            onCreate={createMeter}
            onCancel={() => navigate('../')}
          />
          <MeterInformation path="/:id" />
        </Router>
      </div>
    </ContainerCard>
  );
};

export default UserDashboard;
