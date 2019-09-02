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
  onChange?: (value: string) => void;
  onBlur?: (value: string) => void;
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
        id={id}
        type={type}
        name={name}
        value={value}
        className={styles.input}
        onChange={e => onChange && onChange(e.target.value)}
        onBlur={e => onBlur && onBlur(e.target.value)}
        placeholder={placeholder}
      />
      <span className={styles.error}> {error} </span>
    </div>
  );
};

export default Input;
