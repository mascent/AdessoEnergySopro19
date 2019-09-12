import React from 'react';
import styles from './meter-icon.module.scss';
import Fire from 'mdi-react/FireIcon';
import Water from 'mdi-react/WaterIcon';
import Flash from 'mdi-react/FlashIcon';
import { MeterType } from '../../typings/provider-data-interfaces';

const MeterIcon: React.FC<{ type: MeterType }> = ({ type }) => {
  switch (type) {
    case 'Gas':
      return (
        <div className={styles.container}>
          <Fire />
        </div>
      );
    case 'Water':
      return (
        <div className={styles.container}>
          <Water />
        </div>
      );
    case 'Electricity':
      return (
        <div className={styles.container}>
          <Flash />
        </div>
      );
  }
};

export default MeterIcon;
