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
import { useAuth } from '../../providers/authentication-provider';
import ReadingList from './readings-list';

const MeterInformation: React.FC<RouteComponentProps<{ id: string }>> = ({
  id
}) => {
  const { isAdmin } = useAuth();
  const meter = useMeter(id || '');
  const { readings, isLoading, addReading, updateReading } = useReadings(
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
  const [editReading, setEditReading] = useState<{
    id: string;
    init: string;
  } | null>(null);
  const [dataView, setDataView] = useState<'table' | 'graph'>('table');

  if (!meter) return <Redirect to="/" noThrow />;

  function handleAddReading(value: string) {
    if (typeof id !== 'undefined' && id !== '')
      addReading(id, { value }).then(() => toggleAddReading(false));
  }

  function handleEditReading(id: string, value: string) {
    if (typeof id !== 'undefined' && id !== '' && meter !== null)
      updateReading(meter.meter.id, id, { value }).then(() =>
        toggleAddReading(false)
      );
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
      {isLoading && (
        <div className={styles.spinnerContainer}>
          <Spinner size="large" />
        </div>
      )}
      {!isLoading && (
        <>
          <div className={styles.contentHeader}>
            <div className={styles.flex}>
              <SectionHeader>Readings</SectionHeader>
              <button
                onClick={() =>
                  setDataView(val => (val === 'graph' ? 'table' : 'graph'))
                }
                className={styles.addButton}
              >
                {dataView === 'graph' ? 'Tabelle' : 'Diagramm'}
              </button>
            </div>
            <button
              onClick={() => {
                setEditReading(null);
                toggleAddReading(val => !val);
              }}
              className={styles.addButton}
            >
              Neuen Eintrag erfassen
            </button>
          </div>
          {showAddReading && (
            <NewReading
              initialValue={
                !editReading ? meter.meter.lastReading.value : editReading.init
              }
              onClose={() => toggleAddReading(false)}
              onAdd={r =>
                !editReading
                  ? handleAddReading(r)
                  : handleEditReading(editReading.id, r)
              }
            />
          )}
          {dataView === 'graph' && (
            <Graph data={graphData} dates={graphLabel} title="Readings" />
          )}
          {dataView === 'table' && (
            <ReadingList
              readings={readings}
              canEdit={isAdmin && !showAddReading}
              onEditClick={(id, init) => {
                toggleAddReading(true);
                setEditReading({ id, init });
              }}
            />
          )}
        </>
      )}
    </section>
  );
};

export default MeterInformation;
