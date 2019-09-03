import React from 'react';
import styles from './button.module.scss';
import cx from 'classnames';

interface ButtonProps {
  onClick: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
  disabled?: boolean;
  className?: string;
}

const PrimaryButton: React.FC<ButtonProps> = ({
  onClick,
  children,
  disabled,
  className
}) => {
  return (
    <button
      className={cx(styles.primaryButton, className)}
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
  disabled,
  className
}) => {
  return (
    <button
      className={cx(styles.secondaryButton, className)}
      onClick={onClick}
      disabled={disabled}
    >
      {children}
    </button>
  );
};

export { PrimaryButton, SecondaryButton };
