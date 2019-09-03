import React from 'react';
import styles from './text.module.scss';
import cx from 'classnames';

interface TextProps {
  className?: string;
}

export const Span: React.FC<TextProps> = ({ className, children }) => (
  <span className={cx(styles.span, className)}>{children}</span>
);

export const Paragraph: React.FC<TextProps> = ({ className, children }) => (
  <p className={cx(styles.paragraph, className)}>{children}</p>
);

export const SectionHeader: React.FC<TextProps> = ({ className, children }) => (
  <h3 className={cx(styles.sectionHeader, className)}>{children}</h3>
);

export const SubTitle: React.FC<TextProps> = ({ className, children }) => (
  <h2 className={cx(styles.subTitle, className)}>{children}</h2>
);

export const Title: React.FC<TextProps> = ({ className, children }) => (
  <h1 className={cx(styles.title, className)}>{children}</h1>
);
