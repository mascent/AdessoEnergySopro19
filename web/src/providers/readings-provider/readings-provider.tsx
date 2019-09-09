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
  updateReadingFailure
} from './readings-actions';

interface ReadingsContext {
  readings: Reading[];
  isLoading: boolean;
  error: Error | null;
  fetchReadings: () => Promise<void>;
  addReading: (reading: Partial<Reading>) => Promise<void>;
  updateReading: (id: string, update: Partial<Reading>) => Promise<void>;
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

  const fetchReadings = useCallback(async () => {
    try {
      Logger.logBreadcrumb('info', 'readings-context', 'Fetching readings');
      dispatch(fetchReadingsRequest());
      // Do fetch
      Logger.logBreadcrumb('info', 'readings-context', 'Fetched readings');
      dispatch(fetchReadingsSuccess([]));
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

  const addReading = useCallback(async (meter: Partial<Reading>) => {
    try {
      Logger.logBreadcrumb('info', 'readings-context', 'Adding reading');
      dispatch(addReadingRequest());
      // Do fetch
      Logger.logBreadcrumb('info', 'readings-context', 'Added reading');
      // dispatch(addMeterSuccess());
    } catch (e) {
      Logger.logBreadcrumb('error', 'readings-context', 'Add reading failed');
      Logger.captureException(e);
      dispatch(addReadingFailure(e));
    }
  }, []);

  const updateReading = useCallback(
    async (id: string, update: Partial<Reading>) => {
      try {
        Logger.logBreadcrumb('info', 'readings-context', 'Updating reading');
        dispatch(updateReadingRequest(id));
        // Do fetch
        Logger.logBreadcrumb('info', 'readings-context', 'Updated reading');
        // dispatch(updateMeterSuccess());
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
  addReading: (reading: Partial<Reading>) => Promise<void>;
}

let fetching = false;
export function useMeters(): ReadingsKit {
  const context = useContext(ReadingsContext);

  if (typeof context === 'undefined')
    throw new Error('useUsers must be used within a UsersProvider');

  const { fetchReadings, updateReading, ...rest } = context;

  React.useEffect(() => {
    if (fetching || rest.isLoading || rest.readings !== null) return;

    fetching = true;
    fetchReadings().finally(() => (fetching = false));
  }, [fetchReadings, rest]);

  return rest;
}

interface ReadingKit {
  reading: Reading;
  updateReading: (update: Partial<Reading>) => Promise<void>;
}

export function useMeter(id: string): ReadingKit | null {
  const context = useContext(ReadingsContext);

  if (typeof context === 'undefined')
    throw new Error('useUser must be used within a UsersProvider');

  const { readings, updateReading } = context;

  const reading = useMemo(() => readings.find(reading => reading.id === id), [
    id,
    readings
  ]);

  if (typeof reading === 'undefined') return null;

  return { reading, updateReading: updateReading.bind(undefined, reading.id) };
}
