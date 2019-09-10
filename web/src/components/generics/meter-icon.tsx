import React from 'react';
import styles from './meter-icon.module.scss';
import Fire from 'mdi-react/FireIcon';
import Water from 'mdi-react/WaterIcon';
import Flash from 'mdi-react/FlashIcon';
import { MeterType } from '../../typings/provider-data-interfaces';

const MeterIcon: React.FC<{ type: MeterType }> = ({ type }) => {
  switch (type) {
    case 'gas':
      return (
        <div className={styles.container}>
          <Fire />
        </div>
      );
    case 'water':
      return (
        <div className={styles.container}>
          <Water />
        </div>
      );
    case 'electricity':
      return (
        <div className={styles.container}>
          <Flash />
        </div>
      );
  }
};

export default MeterIcon;
