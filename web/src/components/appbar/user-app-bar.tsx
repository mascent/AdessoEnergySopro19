import React from 'react';
import styles from './user-app-bar.module.scss';
import EmailOutline from 'mdi-react/EmailOutlineIcon';
import Logout from 'mdi-react/LogoutIcon';
import { Title } from '../generics/text';
import { InvButton } from '../generics/button';

const UserAppBar: React.FC = () => {
  return (
    <div className={styles.userAppBar}>
      <nav className={styles.navigation}>
        <Title className={styles.title}>adesso energy</Title>
        <div>
          <InvButton
            className={styles.emailButton}
            onClick={() => console.log('test')}
          >
            <EmailOutline />
          </InvButton>
          <InvButton onClick={() => console.log('test')}>
            <Logout />
          </InvButton>
        </div>
      </nav>
    </div>
  );
};

export default UserAppBar;
