import React from 'react';
import { Route, Switch, withRouter } from 'react-router-dom';
import ContainerCard from './generics/container-card';
import styles from './user-dashboard.module.scss';
import { SelectMeter } from './dashboard-content/select-call';
import MeterList from './meters-list/meter-list';
import { useMeters } from '../providers/meters-provider';
import Spinner from './generics/spinner';

interface UserDashboardProps {
  match: { path: string; params: object; isExact: boolean; url: string };
}

const UserDashboard: React.FC<UserDashboardProps> = ({ match }) => {
  const { meters, isLoading } = useMeters();

  if (isLoading)
    return (
      <ContainerCard className={styles.container}>
        <Spinner />
      </ContainerCard>
    );

  return (
    <ContainerCard className={styles.container}>
      <MeterList meters={meters} onAddMeterClick={() => {}} />
      <div className={styles.contentContainer}>
        <Switch>
          <Route path={match.path} exact component={SelectMeter} />
          <Route
            path={`${match.path}/:id`}
            render={() => <p>Show counter info</p>}
          />
        </Switch>
      </div>
    </ContainerCard>
  );
};

export default withRouter(UserDashboard);
