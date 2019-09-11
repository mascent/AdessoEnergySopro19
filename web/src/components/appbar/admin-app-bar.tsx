import React from 'react';
import styles from './admin-app-bar.module.scss';
import Logout from 'mdi-react/LogoutIcon';
import Logo from '../generics/logo';
import AccountMultiple from 'mdi-react/AccountMultipleIcon';
import TicketConfirmation from 'mdi-react/TicketConfirmationIcon';
import { InvButton } from '../generics/button';
import { navigate } from '@reach/router';
import { useAuth } from '../../providers/authentication-provider';

interface AdminAppBarProps {
  selected: 'users' | 'tickets';
}

const AdminAppBar: React.FC<AdminAppBarProps> = ({ selected }) => {
  const { logout } = useAuth();

  return (
    <div className={styles.adminAppBar}>
      <div className={styles.topContainer}>
        <Logo className={styles.logo} type="with-bg" />
        <InvButton
          title="Benutzer anzeigen"
          onClick={() => navigate('/admin/users')}
        >
          <AccountMultiple />
        </InvButton>
        <InvButton
          title="Tickets anzeigen"
          onClick={() => navigate('/admin/issues')}
          data-active={selected}
        >
          <TicketConfirmation />
        </InvButton>
      </div>
      <InvButton title="Ausloggen" onClick={logout}>
        <Logout />
      </InvButton>
    </div>
  );
};

export default AdminAppBar;
