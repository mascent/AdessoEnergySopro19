import React from 'react';
import styles from './meter-list.module.scss';
import { Meter } from '../../typings/provider-data-interfaces';
import { SubTitle } from '../generics/text';
import MeterItem from './meter-item';

interface MeterListProps {
  meters: Meter[];
  onAddMeterClick: () => void;
}

const MeterList: React.FC<MeterListProps> = ({ meters }) => {
  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <SubTitle>Zähler</SubTitle>
        <button className={styles.addButton}>Neuen Zähler hinzufügen</button>
      </div>

      <nav className={styles.list}>
        {meters.map(meter => (
          <MeterItem
            key={meter.id}
            id={meter.id}
            type={meter.type}
            meterNumber={meter.meterNumber}
            date={meter.lastReading.createdAt.toLocaleDateString()}
            trend={meter.lastReading.trend}
            name={meter.name}
          />
        ))}
      </nav>
    </div>
  );
};

export default MeterList;
