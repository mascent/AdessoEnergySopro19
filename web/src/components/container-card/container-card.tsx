import React from 'react';
import styles from './container-card.module.scss';

const ContainerCard: React.FC = ({ children }) => {
  return <section className={styles.container}>{children}</section>;
};

export default ContainerCard;
