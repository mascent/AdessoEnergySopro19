import React from 'react';
import { storiesOf } from '@storybook/react';
import IssueList from './issue-list';
import { Issue } from '../../typings/provider-data-interfaces';
import { WithRouter } from '../../utils/with-router';
import { buildIssue } from '../../utils/fake-builder';

const issues: Issue[] = [
  buildIssue(),
  buildIssue(),
  buildIssue(),
  buildIssue(),
  buildIssue(),
  buildIssue(),
  buildIssue()
];

storiesOf('Dashboard Content | IssueList', module)
  .add('empty', () => (
    <WithRouter>
      <IssueList issues={[]} />
    </WithRouter>
  ))
  .add('filled', () => (
    <WithRouter>
      <div style={{ height: '70vh' }}>
        <IssueList issues={issues} />
      </div>
    </WithRouter>
  ));
