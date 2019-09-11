import React from 'react';
import styles from './meter-display.module.scss';
import { useMeters } from '../../providers/meters-provider';
import { MeterType } from '../../typings/provider-data-interfaces';
import MeterList from '../meters-list/meter-list';
import { Router, navigate } from '@reach/router';
import { SelectMeter } from './select-call';
import NewMeter from '../meters-list/new-meter';
import MeterInformation from './meter-information';

const MeterDisplay: React.FC<{ userId: string }> = ({ userId }) => {
  const { meters, addMeter } = useMeters(userId);

  function createMeter(
    meterType: MeterType,
    name: string,
    meterNumber: string,
    initialValue: string
  ): void {
    addMeter({
      type: meterType,
      name,
      meterNumber
    });
  }

  return (
    <>
      <MeterList meters={meters} />
      <div className={styles.contentContainer}>
        <Router className={styles.router}>
          <SelectMeter path="/" />
          <NewMeter
            path="/new"
            onCreate={createMeter}
            onCancel={() => navigate('../')}
          />
          <MeterInformation path="/:id" />
        </Router>
      </div>
    </>
  );
};

export default MeterDisplay;
