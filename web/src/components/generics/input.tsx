import React from 'react';
import styles from './input.module.scss';

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
        {label}
      </label>
      <input
        className={styles.input}
        id={id}
        type={type}
        name={name}
        value={value}
        placeholder={placeholder}
        onChange={e => onChange && onChange(e.target.value)}
        onBlur={e => onBlur && onBlur(e.target.value)}
        data-error={typeof error !== 'undefined' && error !== ''}
      />
      <span className={styles.error}>{error}</span>
    </div>
  );
};

export default Input;
