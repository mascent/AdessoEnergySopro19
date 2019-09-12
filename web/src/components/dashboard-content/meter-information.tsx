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
import { useSnackBar } from '../../providers/snackbar-provider';

const MeterInformation: React.FC<RouteComponentProps<{ id: string }>> = ({
  id
}) => {
  const showSnackbar = useSnackBar();
  const { isAdmin } = useAuth();
  const meter = useMeter(id || '');
  const { readings, isLoading, addReading, updateReading } = useReadings(
    meter ? meter.meter.id : ''
  );
  const graphData = useMemo(
    () => (!readings ? [] : readings.map(r => parseInt(r.value, 10))),
    [readings]
  );
  const graphLabel = useMemo(
    () =>
      !readings ? [] : readings.map(r => r.createdAt.toLocaleDateString()),
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
      addReading(id, { value }).then(success => {
        if (success) {
          showSnackbar('success', 'Zählerstand hinzugefügt');
          toggleAddReading(false);
        } else
          showSnackbar('error', 'Zählerstand konnte nicht hinzugefügt werden');
      });
  }

  function handleEditReading(id: string, value: string) {
    if (typeof id !== 'undefined' && id !== '' && meter !== null)
      updateReading(meter.meter.id, id, { value }).then(success => {
        if (success) {
          showSnackbar('success', 'Änderung gespeichert');
          toggleAddReading(false);
        } else
          showSnackbar('error', 'Änderung konnte nicht gespeichert werden');
      });
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
          <div title="Letzer Stand">
            <SectionHeader className={styles.bold}>
              {meter.meter.lastReading.value}
            </SectionHeader>
          </div>
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
              <SectionHeader>Zählerstände</SectionHeader>
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
            <Graph data={graphData} dates={graphLabel} title="Zählerstand" />
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
