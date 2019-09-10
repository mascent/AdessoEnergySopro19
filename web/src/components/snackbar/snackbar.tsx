import React from 'react';
import CloseIcon from 'mdi-react/CloseIcon';
import cx from 'classnames';
import styles from './snackbar.module.scss';

export type SnackbarType = 'warning' | 'success' | 'error' | 'info';

interface SnackbarProps {
  className?: string;
  type: SnackbarType;
  text: string;
  action?: {
    handler: () => void;
    text: string;
  };
  onClose: () => void;
}

const Snackbar: React.FC<SnackbarProps> = ({
  className,
  type,
  text,
  action,
  onClose
}) => {
  return (
    <div
      className={cx(styles.snackbar, className)}
      data-type={type}
      data-testid="snackbar"
    >
      <span className={styles.text}>{text}</span>
      {typeof action !== 'undefined' && (
        <button className={styles.action} onClick={action.handler}>
          {action.text}
        </button>
      )}
      <button title="Close" className={styles.closeButton} onClick={onClose}>
        <CloseIcon />
      </button>
    </div>
  );
};

export default Snackbar;
