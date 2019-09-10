import React from 'react';
import { Route, Switch, withRouter } from 'react-router-dom';
import ContainerCard from './generics/container-card';
import styles from './user-dashboard.module.scss';
import { SubTitle } from './generics/text';
import { SelectMeter } from './dashboard-content/select-call';

interface UserDashboardProps {
  match: { path: string; params: object; isExact: boolean; url: string };
}

const UserDashboard: React.FC<UserDashboardProps> = ({ match }) => {
  return (
    <ContainerCard className={styles.container}>
      <div className={styles.leftContainer}>
        <SubTitle>Zähler</SubTitle>
      </div>
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
