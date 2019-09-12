import React from 'react';
import styles from './meter-display-with-user.module.scss';
import { useMeters } from '../../providers/meters-provider';
import { MeterType } from '../../typings/provider-data-interfaces';
import MeterList from '../meters-list/meter-list';
import { Router, navigate, RouteComponentProps } from '@reach/router';
import NewMeter from '../meters-list/new-meter';
import MeterInformation from './meter-information';
import CustomerInformation from '../customer-information';
import { useUser } from '../../providers/users-provider';
import { useSnackBar } from '../../providers/snackbar-provider';

const MeterDisplayWithUser: React.FC<
  RouteComponentProps<{ userId: string }>
> = ({ userId }) => {
  const { meters, addMeter } = useMeters(userId || '');
  const userKit = useUser(userId || '');
  const showSnackbar = useSnackBar();

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

  function updateUser(
    customerId: string,
    firstName: string,
    lastName: string,
    email: string
  ) {
    if (!userKit) return;

    userKit
      .updateUser({ customerId, firstName, lastName, email })
      .then(success => {
        if (success) showSnackbar('success', 'Kunde bearbeitet');
        else showSnackbar('error', 'Kunde konnte nicht bearbeitet werden');
      });
  }

  if (!userKit) return null;

  return (
    <div className={styles.container}>
      <MeterList meters={meters} />
      <div className={styles.contentContainer}>
        <Router className={styles.router}>
          <CustomerInformation
            path="/"
            onSave={updateUser}
            userInfo={userKit.user}
          />
          <NewMeter
            path="new"
            onCreate={createMeter}
            onCancel={() => navigate('../')}
          />
          <MeterInformation path=":id" />
          <NF default />
        </Router>
      </div>
    </div>
  );
};

const NF: React.FC<RouteComponentProps> = () => <p>Not Found</p>;

export default MeterDisplayWithUser;
