import React from 'react';
import styles from './user-app-bar.module.scss';
import EmailOutline from 'mdi-react/EmailOutlineIcon';
import Logout from 'mdi-react/LogoutIcon';
import { Title } from '../generics/text';
import { InvButton } from '../generics/button';
import TicketModal from '../ticket-modal';

const UserAppBar: React.FC = () => {
  const [isOpen, setOpen] = React.useState(false);

  return (
    <>
      <div className={styles.userAppBar}>
        <nav className={styles.navigation}>
          <Title className={styles.title}>adesso energy</Title>
          <div>
            <InvButton
              className={styles.emailButton}
              onClick={() => setOpen(true)}
            >
              <EmailOutline />
            </InvButton>
            <InvButton onClick={() => console.log('test')}>
              <Logout />
            </InvButton>
          </div>
        </nav>
      </div>
      <TicketModal
        isOpen={isOpen}
        closeModal={() => setOpen(false)}
        onSend={() => {}}
      />
    </>
  );
};

export default UserAppBar;
