import React from 'react';
import styles from './container-card.module.scss';
import cx from 'classnames';

interface ContainerCardProps {
  className?: string;
}

const ContainerCard: React.FC<ContainerCardProps> = ({
  children,
  className
}) => {
  return (
    <section className={cx(styles.container, className)}>{children}</section>
  );
};

export default ContainerCard;
