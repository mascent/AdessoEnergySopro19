import React from 'react';
import cx from 'classnames';
import styles from './spinner.module.scss';

type SpinnerSize = 'small' | 'medium' | 'large';

interface SpinnerProps {
  className?: string;
  size?: SpinnerSize;
}

const Spinner: React.FC<SpinnerProps> = ({ className, size = 'medium' }) => (
  <div
    aria-label="Loading spinner"
    className={cx(styles.spinner, className)}
    data-size={size}
  />
);

export default Spinner;
