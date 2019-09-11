import React from 'react';
import styles from './button.module.scss';
import cx from 'classnames';

type ButtonProps = {
  className?: string;
  children?: React.ReactNode;
} & React.ButtonHTMLAttributes<HTMLButtonElement>;

const InvButton = React.forwardRef<HTMLButtonElement, ButtonProps>(
  ({ children, className, ...props }, ref) => {
    return (
      <button ref={ref} className={cx(styles.invButton, className)} {...props}>
        {children}
      </button>
    );
  }
);

const PrimaryButton = React.forwardRef<HTMLButtonElement, ButtonProps>(
  ({ children, className, ...props }, ref) => {
    return (
      <button
        ref={ref}
        className={cx(styles.primaryButton, className)}
        {...props}
      >
        {children}
      </button>
    );
  }
);

const SecondaryButton = React.forwardRef<HTMLButtonElement, ButtonProps>(
  ({ children, className, ...props }, ref) => {
    return (
      <button
        ref={ref}
        className={cx(styles.secondaryButton, className)}
        {...props}
      >
        {children}
      </button>
    );
  }
);

export { PrimaryButton, SecondaryButton, InvButton };
