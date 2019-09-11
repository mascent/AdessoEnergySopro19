import React from 'react';
import cx from 'classnames';
import styles from './user-item.module.scss';
import { Span } from '../generics/text';
import Chevron from 'mdi-react/ChevronRightIcon';
import { Link } from '@reach/router';

interface UserItemProps {
  id: string;
  firstName: string;
  lastName: string;
  kundenNummer: string;
}

const UserItem: React.FC<UserItemProps> = ({
  id,
  firstName,
  lastName,
  kundenNummer
}) => {
  return (
    <Link
      to={`/${id}`}
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
          <Span className={styles.bold}>{`${firstName} ${lastName}`}</Span>
        </div>
        <div className={styles.textContainer}>
          <Span>{kundenNummer}</Span>
        </div>
      </div>
      <Chevron className={styles.arrow} />
    </Link>
  );
};

export default UserItem;
