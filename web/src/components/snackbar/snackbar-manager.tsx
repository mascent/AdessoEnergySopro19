import React from 'react';
import styles from './snackbars-manager.module.scss';

import { TransitionGroup, CSSTransition } from 'react-transition-group';

import Snackbar from './snackbar';
import { SnackBar } from '../../providers/snackbar-provider';

interface SnackbarsManagerProps {
  snackBars: SnackBar[];
  hide: (id: string) => void;
}

const SnackbarsManager: React.FC<SnackbarsManagerProps> = ({
  snackBars,
  hide
}) => (
  <TransitionGroup className={styles.snackbarsContainer}>
    {snackBars.map(snackBar => (
      <CSSTransition key={snackBar.id} timeout={200} classNames="snackbar-">
        <Snackbar
          type={snackBar.type}
          text={snackBar.text}
          action={snackBar.action}
          onClose={() => hide(snackBar.id)}
        />
      </CSSTransition>
    ))}
  </TransitionGroup>
);

export default SnackbarsManager;
