import React from 'react';
import styles from './button.module.scss';

interface ButtonProps {
  onClick: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
  disabled?: boolean;
}

const PrimaryButton: React.FC<ButtonProps> = ({
  onClick,
  children,
  disabled
}) => {
  return (
    <button
      className={styles.primaryButton}
      onClick={onClick}
      disabled={disabled}
    >
      {children}
    </button>
  );
};

const SecondaryButton: React.FC<ButtonProps> = ({
  onClick,
  children,
  disabled
}) => {
  return (
    <button
      className={styles.secondaryButton}
      onClick={onClick}
      disabled={disabled}
    >
      {children}
    </button>
  );
};

export { PrimaryButton, SecondaryButton };
