import React from 'react';
import cx from 'classnames';
import styles from './meter-item.module.scss';
import { Span } from '../generics/text';
import Chevron from 'mdi-react/ChevronRightIcon';
import { Link } from '@reach/router';
import { MeterType } from '../../typings/provider-data-interfaces';
import MeterIcon from '../generics/meter-icon';

interface MeterItemProps {
  id: string;
  type: MeterType;
  name: string;
  meterNumber: string;
  date: string;
  trend: number;
}

const MeterItem: React.FC<MeterItemProps> = ({
  id,
  type,
  name,
  meterNumber,
  date,
  trend
}) => {
  return (
    <Link
      to={`${id}`}
      getProps={({ isCurrent }) => {
        return {
          className: cx({
            [styles.container]: true,
            [styles.activeContainer]: isCurrent
          })
        };
      }}
    >
      <MeterIcon type={type} />
      <div className={styles.infoContainer}>
        <div className={styles.textContainer}>
          <Span className={styles.bold}>{name}</Span>
          <Span>{date}</Span>
        </div>
        <div className={styles.textContainer}>
          <Span>{meterNumber}</Span>
          <Span
            className={cx({
              [styles.goodTrend]: trend < 0,
              [styles.badTrend]: trend > 0
            })}
          >{`${trend > 0 ? `+${trend}` : trend}%`}</Span>
        </div>
      </div>
      <Chevron className={styles.arrow} />
    </Link>
  );
};

export default MeterItem;
