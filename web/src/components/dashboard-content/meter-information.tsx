import React from 'react';
import cx from 'classnames';
import styles from './meter-information.module.scss';
import MeterIcon from '../generics/meter-icon';
import { Span, SectionHeader, SubTitle } from '../generics/text';
import { RouteComponentProps, Redirect } from '@reach/router';
import { useMeter } from '../../providers/meters-provider';

const MeterInformation: React.FC<RouteComponentProps<{ id: string }>> = ({
  id
}) => {
  const meter = useMeter(id || '');

  if (!meter) return <Redirect to="/" noThrow />;

  return (
    <section>
      <header className={styles.headerContainer}>
        <div className={styles.leftGroup}>
          <MeterIcon type={meter.meter.type} />
          <div className={styles.headerTextGroup}>
            <SubTitle>{meter.meter.name}</SubTitle>
            <Span>{meter.meter.meterNumber}</Span>
          </div>
        </div>
        <div className={cx(styles.headerTextGroup, styles.textRight)}>
          <Span className={styles.bold}>{meter.meter.lastReading.value}</Span>
          <Span
            className={cx({
              [styles.goodTrend]: meter.meter.lastReading.trend < 0,
              [styles.badTrend]: meter.meter.lastReading.trend > 0
            })}
          >{`${
            meter.meter.lastReading.trend > 0
              ? `+${meter.meter.lastReading.trend}`
              : meter.meter.lastReading.trend
          }%`}</Span>
        </div>
      </header>
      <div className={styles.contentHeader}>
        <SectionHeader>Readings</SectionHeader>
        <button className={styles.addButton}>Neuen Eintrag erfassen</button>
      </div>
    </section>
  );
};

export default MeterInformation;
