import React from 'react';
import styles from './input.module.scss';
import { string } from 'prop-types';

interface InputProps {
  id: string;
  name?: string;
  type: string;
  placeholder?: string;
  label: string;
  value?: string;
  error?: string;
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onBlur?: (event: React.FocusEvent<HTMLInputElement>) => void;
}

const Input: React.FC<InputProps> = ({
  id,
  type,
  placeholder,
  value,
  name,
  onChange,
  onBlur,
  label,
  error
}) => {
  return (
    <div className={styles.container}>
      <label className={styles.label} htmlFor={id}>
        {label}{' '}
      </label>
      <input
        id="InputLabel"
        className={styles.input}
        onChange={onChange}
        onBlur={onBlur}
      />
      <span className={styles.error}> {error} </span>
    </div>
  );
};

export default Input;
