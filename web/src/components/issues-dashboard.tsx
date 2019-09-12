import React from 'react';
import styles from './issues-dashboard.module.scss';
import ContainerCard from './generics/container-card';
import IssueList from './issue-list/issue-list';
import { useIssues } from '../providers/issues-provider';
import { Router } from '@reach/router';
import IssueInformation from './dashboard-content/issue-information';
import { SelectIssue } from './dashboard-content/select-call';

const IssuesDashboard: React.FC = () => {
  const { issues } = useIssues();

  return (
    <ContainerCard className={styles.container}>
      <IssueList issues={issues} />
      <div className={styles.contentContainer}>
        <Router className={styles.router} basepath="/admin/issues">
          <SelectIssue path="/" />
          <IssueInformation path="/:id" />
        </Router>
      </div>
    </ContainerCard>
  );
};

export default IssuesDashboard;
