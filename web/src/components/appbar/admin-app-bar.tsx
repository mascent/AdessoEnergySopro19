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
      <div className={styles.topContainer}>
        <Logo className={styles.logo} type="with-bg" />
        <InvButton
          title="Benutzer anzeigen"
          onClick={() => console.log('test')}
        >
          <AccountMultiple />
        </InvButton>
        <InvButton title="Tickets anzeigen" onClick={() => console.log('test')}>
          <TicketConfirmation />
        </InvButton>
      </div>
      <InvButton title="Ausloggen" onClick={() => console.log('test')}>
        <Logout />
      </InvButton>
    </div>
  );
};

export default AdminAppBar;
