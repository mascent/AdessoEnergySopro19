import React from 'react';
import { Issue } from '../../typings/provider-data-interfaces';
import styles from './issue-list.module.scss';
import { SubTitle } from '../generics/text';
import IssueItem from './issue-item';

interface IssueListProps {
  issues: Issue[];
}

const UserList: React.FC<IssueListProps> = ({ issues }) => {
  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <SubTitle>Offene Tickets</SubTitle>
      </div>

      <nav className={styles.list}>
        {issues.map(issue => (
          <IssueItem id={issue.id} name={issue.name} subject={issue.subject} />
        ))}
      </nav>
    </div>
  );
};

export default UserList;
