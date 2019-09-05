import React from 'react';
import styles from './user-app-bar.module.scss';
import EmailOutline from 'mdi-react/EmailOutlineIcon';
import Logout from 'mdi-react/LogoutIcon';
import { Title } from '../generics/text';
import { InvButton } from '../generics/button';

const UserAppBar: React.FC = () => {
  return (
    <div className={styles.userAppBar}>
      <Title className={styles.title}> adesso energy </Title>
      <InvButton onClick={() => console.log('test')}>
        <EmailOutline className={styles.emailButton} color="white" />
      </InvButton>
      <InvButton onClick={() => console.log('test')}>
        <Logout color="white" />
      </InvButton>
    </div>
  );
};

export default UserAppBar;
