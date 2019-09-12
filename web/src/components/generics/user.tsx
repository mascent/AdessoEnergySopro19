import React from 'react';
import styles from './user.module.scss';
import ContainerCard from './container-card';
import { SubTitle } from './text';
import { Span } from './text';
import { InvButton } from './button';
import Arrow from 'mdi-react/ArrowRightIcon';

interface userProps {
  name: string;
  kundennummer: string;
}
const User: React.FC<userProps> = ({ name, kundennummer }) => {
  return (
    <InvButton
      onClick={() => console.log('Auf Kunden geklickt..')}
      className={styles.button}
    >
      <ContainerCard className={styles.container}>
        <SubTitle className={styles.name}>{name}</SubTitle>
        <Span className={styles.nummer}>{kundennummer}</Span>
        <Arrow color="black" size="30px" className={styles.arrow} />
      </ContainerCard>
    </InvButton>
  );
};

export default User;
