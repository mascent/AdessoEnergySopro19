import React, { useMemo, useState } from 'react';
import cx from 'classnames';
import styles from './meter-information.module.scss';
import MeterIcon from '../generics/meter-icon';
import { Span, SectionHeader, SubTitle } from '../generics/text';
import { RouteComponentProps, Redirect } from '@reach/router';
import { useMeter } from '../../providers/meters-provider';
import Graph from '../graph/graph';
import { useReadings } from '../../providers/readings-provider';
import Spinner from '../generics/spinner';
import NewReading from '../new-reading';

const MeterInformation: React.FC<RouteComponentProps<{ id: string }>> = ({
  id
}) => {
  const meter = useMeter(id || '');
  const { readings, isLoading, addReading } = useReadings(
    meter ? meter.meter.id : ''
  );
  const graphData = useMemo(() => readings.map(r => parseInt(r.value, 10)), [
    readings
  ]);
  const graphLabel = useMemo(
    () => readings.map(r => r.createdAt.toLocaleDateString()),
    [readings]
  );

  const [showAddReading, toggleAddReading] = useState(false);

  if (!meter) return <Redirect to="/" noThrow />;

  function handleAddReading(value: string) {
    if (typeof id !== 'undefined' && id !== '')
      addReading(id, { value }).then(() => toggleAddReading(false));
  }

  return (
    <section className={styles.mainContainer}>
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
      {isLoading && <Spinner size="large" />}
      {!isLoading && (
        <>
          <div className={styles.contentHeader}>
            <SectionHeader>Readings</SectionHeader>
            <button
              onClick={() => toggleAddReading(val => !val)}
              className={styles.addButton}
            >
              Neuen Eintrag erfassen
            </button>
          </div>
          {showAddReading && (
            <NewReading
              onClose={() => toggleAddReading(false)}
              onAdd={handleAddReading}
            />
          )}
          <Graph data={graphData} dates={graphLabel} title="Readings" />
        </>
      )}
    </section>
  );
};

export default MeterInformation;
