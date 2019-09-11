import React from 'react';
import { User } from '../../typings/provider-data-interfaces';
import styles from './user-list.module.scss';
import { SubTitle } from '../generics/text';
import UserItem from './user-item';
import { Link } from '@reach/router';

interface UserListProps {
  users: User[];
}

const UserList: React.FC<UserListProps> = ({ users }) => {
  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <SubTitle>Kunden</SubTitle>
        <Link className={styles.addLink} to="new">
          Neuen Kunden erfassen
        </Link>
      </div>

      <nav className={styles.list}>
        {users.map(user => (
          <UserItem
            key={user.id}
            id={user.id}
            firstName={user.firstName}
            lastName={user.lastName}
            kundenNummer={user.customerId}
          />
        ))}
      </nav>
    </div>
  );
};

export default UserList;
