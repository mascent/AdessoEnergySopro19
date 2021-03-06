import React from 'react';
import styles from './user-app-bar.module.scss';
import EmailOutline from 'mdi-react/EmailOutlineIcon';
import Logout from 'mdi-react/LogoutIcon';
import { Title } from '../generics/text';
import { InvButton } from '../generics/button';
import TicketModal from '../ticket-modal';
import { useCreateIssue } from '../../providers/issues-provider';
import { useAuth } from '../../providers/authentication-provider';
import { useSnackBar } from '../../providers/snackbar-provider';
import Logo from '../generics/logo';

const UserAppBar: React.FC = () => {
  const [isOpen, setOpen] = React.useState(false);
  const { logout } = useAuth();
  const showSnackbar = useSnackBar();

  const create = useCreateIssue();
  function handleSend(
    name: string,
    email: string,
    subject: string,
    message: string
  ) {
    create({ name, email, subject, message }).then(success => {
      if (success) {
        showSnackbar('success', 'Ticket wurde erstellt');
        setOpen(false);
      } else showSnackbar('error', 'Ticket konnte nicht erstellt werden');
    });
  }

  return (
    <>
      <div className={styles.userAppBar}>
        <nav className={styles.navigation}>
          <div className={styles.logoText}>
            <Logo type="with-bg" className={styles.logo} />
            <Title className={styles.title}>adesso energy</Title>
          </div>
          <div>
            <InvButton
              className={styles.emailButton}
              onClick={() => setOpen(true)}
            >
              <EmailOutline />
            </InvButton>
            <InvButton onClick={logout}>
              <Logout />
            </InvButton>
          </div>
        </nav>
      </div>
      <TicketModal
        isOpen={isOpen}
        closeModal={() => setOpen(false)}
        onSend={handleSend}
      />
    </>
  );
};

export default UserAppBar;
