import React from 'react';
import { RouteComponentProps, Redirect, navigate } from '@reach/router';
import { useIssue } from '../../providers/issues-provider';
import { SubTitle, Span, Paragraph } from '../generics/text';
import { SecondaryButton, PrimaryButton } from '../generics/button';
import styles from './issue-information.module.scss';

const IssueInformation: React.FC<RouteComponentProps<{ id: string }>> = ({
  id
}) => {
  const issue = useIssue(id || '');

  if (!issue) {
    return <Redirect to="/" noThrow />;
  }

  function handleRequest() {
    if (!issue) return;
    navigate(`mailto:${issue.issue.email}`);
  }

  return (
    <div className={styles.main}>
      <div className={styles.header}>
        <SubTitle>Betreff des Tickets</SubTitle>
        <div>
          <SecondaryButton
            className={styles.secondButton}
            onClick={handleRequest}
          >
            Rückfrage stellen
          </SecondaryButton>
          <PrimaryButton
            disabled={issue.issue.state === 'RESOLVED'}
            onClick={() => issue.updateIssue({ state: 'RESOLVED' })}
          >
            {issue.issue.state === 'RESOLVED' ? 'Erledigt' : 'Schließen'}
          </PrimaryButton>
        </div>
      </div>
      <div className={styles.nameText}>
        <Span className={styles.bold}> Name des Erstellers </Span>
        <Span>{issue.issue.name}</Span>
      </div>
      <div className={styles.emailText}>
        <Span className={styles.bold}> Email des Erstellers </Span>
        <Span>{issue.issue.email}</Span>
      </div>
      <div className={styles.message}>
        <Span className={styles.bold}>Nachricht</Span>
        <Paragraph> {issue.issue.message} </Paragraph>
      </div>
    </div>
  );
};

export default IssueInformation;
