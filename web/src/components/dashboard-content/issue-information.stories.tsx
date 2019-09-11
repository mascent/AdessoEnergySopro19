import React from 'react';
import { storiesOf } from '@storybook/react';
import IssueInformation from './issue-information';
import { IssuesProvider } from '../../providers/issues-provider';
import { boolean } from '@storybook/addon-knobs';
import { buildIssue, buildList } from '../../utils/fake-builder';

const issues = buildList(buildIssue, 5, 100);

storiesOf('Dashboard Content | Issue Information', module).add(
  'default',
  () => (
    <IssuesProvider
      override={{
        isLoading: boolean('Loading', false),
        issues: issues,
        addIssue: async () => {},
        fetchIssues: async () => {},
        updateIssue: async () => {}
      }}
    >
      <IssueInformation id={issues.length > 0 ? issues[0].id : ''} />
    </IssuesProvider>
  )
);
