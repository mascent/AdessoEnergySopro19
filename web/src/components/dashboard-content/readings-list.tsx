import React from 'react';
import cx from 'classnames';
import styles from './readings-list.module.scss';
import { Reading } from '../../typings/provider-data-interfaces';
import EditIcon from 'mdi-react/EditIcon';

interface ReadingList {
  readings: Reading[];
  canEdit?: boolean;
  onEditClick?: (id: string, value: string) => void;
}

const ReadingList: React.FC<ReadingList> = ({
  readings,
  canEdit,
  onEditClick
}) => {
  return (
    <table className={styles.table}>
      <thead>
        <tr>
          <th className={styles.headText}>Erfasst am</th>
          <th className={styles.headText}>Wert in kWz</th>
          <th className={styles.headText}>Tendenz</th>
          {canEdit && <th></th>}
        </tr>
      </thead>
      <tbody>
        {readings.map(reading => (
          <tr className={styles.row} key={reading.id}>
            <td className={styles.tableText}>
              {reading.createdAt.toLocaleDateString()}
            </td>
            <td className={styles.tableText}>{reading.value}</td>
            <td
              className={cx({
                [styles.tableText]: true,
                [styles.goodTrend]: reading.trend < 0,
                [styles.badTrend]: reading.trend > 0
              })}
            >{`${
              reading.trend > 0 ? `+${reading.trend}` : reading.trend
            }%`}</td>
            {canEdit && onEditClick && (
              <td className={styles.padding}>
                <button
                  className={styles.button}
                  title="Edit reading"
                  onClick={onEditClick.bind(
                    undefined,
                    reading.id,
                    reading.value
                  )}
                >
                  <EditIcon />
                </button>
              </td>
            )}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default ReadingList;
