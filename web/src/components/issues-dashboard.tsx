import React from 'react';
import styles from './issues-dashboard.module.scss';
import ContainerCard from './generics/container-card';
import IssueList from './issue-list/issue-list';
import { useIssues } from '../providers/issues-provider';
import { Issue } from '../typings/provider-data-interfaces';
import { Router, navigate } from '@reach/router';
import IssueInformation from './dashboard-content/issue-information';
import { SelectMeter } from './dashboard-content/select-call';
import NewMeter from './meters-list/new-meter';

const IssuesDashboard: React.FC = () => {
  const { issues } = useIssues();

  return (
    <ContainerCard className={styles.container}>
      <IssueList issues={issues} />
      <div className={styles.contentContainer}>
        <Router className={styles.router} basepath="/admin/issues">
          <SelectMeter path="/" />
          <IssueInformation path="/:id" />
        </Router>
      </div>
    </ContainerCard>
  );
};

export default IssuesDashboard;
