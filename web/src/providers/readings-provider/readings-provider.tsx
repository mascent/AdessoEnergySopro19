import React, { useReducer, useContext, useMemo, useCallback } from 'react';
import { Reading } from '../../typings/provider-data-interfaces';
import { readingsReducer, ReadingsState } from './readings-reducer';
import Logger from '../../services/logger/logger';
import {
  fetchReadingsRequest,
  fetchReadingsSuccess,
  fetchReadingsFailure,
  addReadingRequest,
  addReadingFailure,
  updateReadingRequest,
  updateReadingFailure,
  addReadingSuccess,
  updateReadingSuccess
} from './readings-actions';
import { readings } from '../../services/ad-api';
import {
  mapReadingDTOtoReading,
  mapInternalReadingToReadingDTO
} from '../../lib/mappers';

interface ReadingsContext {
  readings: Reading[];
  isLoading: boolean;
  error: Error | null;
  fetchReadings: (meterId: string) => Promise<void>;
  addReading: (meterId: string, reading: Partial<Reading>) => Promise<void>;
  updateReading: (
    meterId: string,
    id: string,
    update: Partial<Reading>
  ) => Promise<void>;
}

const initialContext: ReadingsState = {
  readings: [],
  isLoading: false,
  error: null
};

const ReadingsContext = React.createContext<ReadingsContext | undefined>(
  undefined
);

interface ReadingsProviderProps {
  override?: ReadingsContext;
}

export const ReadingsProvider: React.FC<ReadingsProviderProps> = ({
  override,
  children
}) => {
  const [state, dispatch] = useReducer(readingsReducer, initialContext);

  const fetchReadings = useCallback(async (meterId: string) => {
    try {
      Logger.logBreadcrumb('info', 'readings-context', 'Fetching readings');
      dispatch(fetchReadingsRequest());

      const result = await readings.getAllReadingsByAMeter(meterId);
      Logger.logBreadcrumb('info', 'readings-context', 'Fetched readings');
      dispatch(
        fetchReadingsSuccess(result.map(res => mapReadingDTOtoReading(res)))
      );
    } catch (e) {
      Logger.logBreadcrumb(
        'error',
        'readings-context',
        'Fetch readings failed'
      );
      Logger.captureException(e);
      dispatch(fetchReadingsFailure(e));
    }
  }, []);

  const addReading = useCallback(
    async (meterId: string, reading: Partial<Reading>) => {
      try {
        Logger.logBreadcrumb('info', 'readings-context', 'Adding reading');
        dispatch(addReadingRequest());

        const res = await readings.addReadingToAMeter(
          meterId,
          mapInternalReadingToReadingDTO(reading)
        );
        Logger.logBreadcrumb('info', 'readings-context', 'Added reading');
        dispatch(addReadingSuccess(mapReadingDTOtoReading(res)));
      } catch (e) {
        Logger.logBreadcrumb('error', 'readings-context', 'Add reading failed');
        Logger.captureException(e);
        dispatch(addReadingFailure(e));
      }
    },
    []
  );

  const updateReading = useCallback(
    async (meterId: string, id: string, update: Partial<Reading>) => {
      try {
        Logger.logBreadcrumb('info', 'readings-context', 'Updating reading');
        dispatch(updateReadingRequest(id));

        const res = await readings.updateReadingForAMeter(
          meterId,
          id,
          mapInternalReadingToReadingDTO(update)
        );
        Logger.logBreadcrumb('info', 'readings-context', 'Updated reading');
        dispatch(updateReadingSuccess(mapReadingDTOtoReading(res)));
      } catch (e) {
        Logger.logBreadcrumb(
          'error',
          'readings-context',
          'Update reading failed'
        );
        Logger.captureException(e);
        dispatch(updateReadingFailure(id, e));
      }
    },
    []
  );

  // Override the internal state with a possible override for test purposes
  const context = useMemo(
    () => ({ ...state, fetchReadings, addReading, updateReading, ...override }),
    [state, fetchReadings, addReading, updateReading, override]
  );

  return (
    <ReadingsContext.Provider value={context}>
      {children}
    </ReadingsContext.Provider>
  );
};

interface ReadingsKit {
  readings: Reading[];
  isLoading: boolean;
  error: Error | null;
  addReading: (meterId: string, reading: Partial<Reading>) => Promise<void>;
}

let fetching = false;
export function useReadings(meterId: string): ReadingsKit {
  const context = useContext(ReadingsContext);

  if (typeof context === 'undefined')
    throw new Error('useReadings must be used within a ReadingsProvider');

  const { fetchReadings, updateReading, ...rest } = context;

  React.useEffect(() => {
    if (fetching || rest.isLoading || rest.readings.length !== 0) return;

    fetching = true;
    fetchReadings(meterId).finally(() => (fetching = false));
  }, [fetchReadings, rest, meterId]);

  return rest;
}

interface ReadingKit {
  reading: Reading;
  updateReading: (update: Partial<Reading>) => Promise<void>;
}

export function useReading(id: string): ReadingKit | null {
  const context = useContext(ReadingsContext);

  if (typeof context === 'undefined')
    throw new Error('useReading must be used within a ReadingsProvider');

  const { readings, updateReading } = context;

  const reading = useMemo(() => readings.find(reading => reading.id === id), [
    id,
    readings
  ]);

  if (typeof reading === 'undefined') return null;

  return {
    reading,
    updateReading: updateReading.bind(undefined, reading.meterId, reading.id)
  };
}
