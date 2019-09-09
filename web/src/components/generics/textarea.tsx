import React from 'react';
import styles from './textarea.module.scss';
import cx from 'classnames';

interface TextareaProps {
  className?: string;
  id: string;
  name?: string;
  placeholder?: string;
  label: string;
  value?: string;
  error?: string | null;
  onChange?: (value: string) => void;
  onBlur?: (value: string) => void;
}

const Textarea: React.FC<TextareaProps> = ({
  className,
  id,
  placeholder,
  value,
  name,
  onChange,
  onBlur,
  label,
  error
}) => {
  return (
    <div className={cx(styles.container, className)}>
      <label className={styles.label} htmlFor={id}>
        {label}
      </label>
      <textarea
        className={styles.textarea}
        id={id}
        name={name}
        placeholder={placeholder}
        onChange={e => onChange && onChange(e.target.value)}
        onBlur={e => onBlur && onBlur(e.target.value)}
        data-error={typeof error === 'string' && error.trim() !== ''}
        rows={4}
      >
        {value}
      </textarea>
      <span className={styles.error}>{error}</span>
    </div>
  );
};

export default Textarea;
