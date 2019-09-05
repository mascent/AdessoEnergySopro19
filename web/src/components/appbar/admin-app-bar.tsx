import React from 'react';
import styles from './admin-app-bar.module.scss';
import Logout from 'mdi-react/LogoutIcon';
import Logo from '../generics/logo';
import AccountMultiple from 'mdi-react/AccountMultipleIcon';
import TicketConfirmation from 'mdi-react/TicketConfirmationIcon';
import { InvButton } from '../generics/button';

const AdminAppBar: React.FC = () => {
  return (
    <div className={styles.adminAppBar}>
      <Logo className={styles.logo} type="with-bg" />
      <div className={styles.icons}>
        <InvButton onClick={() => console.log('test')}>
          <AccountMultiple color="white" />
        </InvButton>
        <InvButton onClick={() => console.log('test')}>
          <TicketConfirmation color="white" />
        </InvButton>
      </div>
      <InvButton onClick={() => console.log('test')}>
        <Logout color="white" />
      </InvButton>
    </div>
  );
};

export default AdminAppBar;
