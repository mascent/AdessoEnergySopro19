import React from 'react';
import styles from './button.module.scss';
import cx from 'classnames';

interface ButtonProps {
  onClick: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
  disabled?: boolean;
  className?: string;
  children?: React.ReactNode;
}

const PrimaryButton = React.forwardRef<HTMLButtonElement, ButtonProps>(
  ({ onClick, children, disabled, className }, ref) => {
    return (
      <button
        ref={ref}
        className={cx(styles.primaryButton, className)}
        onClick={onClick}
        disabled={disabled}
      >
        {children}
      </button>
    );
  }
);

const SecondaryButton = React.forwardRef<HTMLButtonElement, ButtonProps>(
  ({ onClick, children, disabled, className }, ref) => {
    return (
      <button
        ref={ref}
        className={cx(styles.secondaryButton, className)}
        onClick={onClick}
        disabled={disabled}
      >
        {children}
      </button>
    );
  }
);

export { PrimaryButton, SecondaryButton };
