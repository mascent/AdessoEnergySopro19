import React from 'react';
import styles from './container-card.module.scss';

const ContainerCard: React.FC<{ className?: string }> = ({
  className,
  children
}) => {
  return (
    <section
      className={`${styles.container} ${
        typeof className !== 'undefined' ? className : ''
      }`}
    >
      {children}
    </section>
  );
};

export default ContainerCard;
