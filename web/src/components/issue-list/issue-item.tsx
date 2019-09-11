import React from 'react';
import cx from 'classnames';
import styles from './issue-item.module.scss';
import { Span } from '../generics/text';
import Chevron from 'mdi-react/ChevronRightIcon';
import { Link } from '@reach/router';

interface IssueItemProps {
  id: string;
  subject: string;
  name: string;
}

const IssueItem: React.FC<IssueItemProps> = ({ id, name, subject }) => {
  return (
    <Link
      to={`${id}`}
      getProps={({ isCurrent }) => {
        return {
          className: cx({
            [styles.container]: true,
            [styles.activeContainer]: isCurrent
          })
        };
      }}
    >
      <div className={styles.infoContainer}>
        <div className={styles.textContainer}>
          <Span className={styles.bold}>{subject}</Span>
        </div>
        <div className={styles.textContainer}>
          <Span> {`${name}`}</Span>
        </div>
      </div>
      <Chevron className={styles.arrow} />
    </Link>
  );
};

export default IssueItem;
