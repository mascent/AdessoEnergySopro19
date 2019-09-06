import React from 'react';
import styles from './meters.module.scss';
import ContainerCard from './container-card';
import Fire from 'mdi-react/FireIcon';
import Water from 'mdi-react/WaterIcon';
import Flash from 'mdi-react/FlashIcon';
import { Span } from './text';
import { InvButton } from './button';
import Arrow from 'mdi-react/ArrowRightIcon';

export type MeterType = 'strom' | 'gas' | 'water';

interface metersProps {
  type: MeterType;
  name: string;
  meterNumber: string;
  date: string;
  compared: string;
}

const Meters: React.FC<metersProps> = ({
  type,
  name,
  meterNumber,
  date,
  compared
}) => {
  switch (type) {
    case 'gas':
      return (
        <InvButton
          className={styles.button}
          onClick={() => console.log('Zählerstand geklickt..')}
        >
          <ContainerCard className={styles.container}>
            <Fire color="black" size="50px" />
            <Span className={styles.textTitle}>{name} </Span>
            <Span className={styles.textZählerstand}>{meterNumber}</Span>
            <Span className={styles.textDatum}>{date}</Span>
            <Span className={styles.compared}> {compared}</Span>
            <Arrow color="black" className={styles.arrow} />
          </ContainerCard>
        </InvButton>
      );
    case 'strom':
      return (
        <InvButton
          className={styles.button}
          onClick={() => console.log('Zählerstand geklickt..')}
        >
          <ContainerCard className={styles.container}>
            <Water color="black" size="50px" />
            <Span className={styles.textTitle}>{name} </Span>
            <Span className={styles.textZählerstand}>{meterNumber}</Span>
            <Span className={styles.textDatum}>{date}</Span>
            <Span className={styles.compared}> {compared}</Span>
            <Arrow color="black" className={styles.arrow} />
          </ContainerCard>
        </InvButton>
      );
    case 'water':
      return (
        <InvButton
          className={styles.button}
          onClick={() => console.log('Zählerstand geklickt..')}
        >
          <ContainerCard className={styles.container}>
            <Flash color="black" size="50px" />
            <Span className={styles.textTitle}>{name}</Span>
            <Span className={styles.textZählerstand}>{meterNumber}</Span>
            <Span className={styles.textDatum}>{date}</Span>
            <Span className={styles.compared}> {compared}</Span>
            <Arrow color="black" className={styles.arrow} />
          </ContainerCard>
        </InvButton>
      );
  }
};

export default Meters;
