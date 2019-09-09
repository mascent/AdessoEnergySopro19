import React, { useReducer, useContext, useMemo, useCallback } from 'react';
import { Meter } from '../../typings/provider-data-interfaces';
import { metersReducer, MetersState } from './meters-reducer';
import Logger from '../../services/logger/logger';
import {
  fetchMetersRequest,
  fetchMetersSuccess,
  fetchMetersFailure,
  addMeterRequest,
  addMeterFailure,
  updateMeterRequest,
  updateMeterFailure
} from './meters-action';

interface MetersContext {
  meters: Meter[];
  isLoading: boolean;
  error: Error | null;
  fetchMeters: () => Promise<void>;
  addMeter: (meter: Partial<Meter>) => Promise<void>;
  updateMeter: (id: string, update: Partial<Meter>) => Promise<void>;
}

const initialContext: MetersState = {
  meters: [],
  isLoading: false,
  error: null
};

const MetersContext = React.createContext<MetersContext | undefined>(undefined);

interface MetersProviderProps {
  override?: MetersContext;
}

export const MetersProvider: React.FC<MetersProviderProps> = ({
  override,
  children
}) => {
  const [state, dispatch] = useReducer(metersReducer, initialContext);

  const fetchMeters = useCallback(async () => {
    try {
      Logger.logBreadcrumb('info', 'meters-context', 'Fetching meters');
      dispatch(fetchMetersRequest());
      // Do fetch
      Logger.logBreadcrumb('info', 'meters-context', 'Fetched meters');
      dispatch(fetchMetersSuccess([]));
    } catch (e) {
      Logger.logBreadcrumb('error', 'meters-context', 'Fetch meters failed');
      Logger.captureException(e);
      dispatch(fetchMetersFailure(e));
    }
  }, []);

  const addMeter = useCallback(async (meter: Partial<Meter>) => {
    try {
      Logger.logBreadcrumb('info', 'meters-context', 'Adding meter');
      dispatch(addMeterRequest());
      // Do fetch
      Logger.logBreadcrumb('info', 'meters-context', 'Added meter');
      // dispatch(addMeterSuccess());
    } catch (e) {
      Logger.logBreadcrumb('error', 'meters-context', 'Add meter failed');
      Logger.captureException(e);
      dispatch(addMeterFailure(e));
    }
  }, []);

  const updateMeter = useCallback(
    async (id: string, update: Partial<Meter>) => {
      try {
        Logger.logBreadcrumb('info', 'meters-context', 'Updating user');
        dispatch(updateMeterRequest(id));
        // Do fetch
        Logger.logBreadcrumb('info', 'meters-context', 'Updated user');
        // dispatch(updateMeterSuccess());
      } catch (e) {
        Logger.logBreadcrumb('error', 'meters-context', 'Update user failed');
        Logger.captureException(e);
        dispatch(updateMeterFailure(id, e));
      }
    },
    []
  );

  // Override the internal state with a possible override for test purposes
  const context = useMemo(
    () => ({ ...state, fetchMeters, addMeter, updateMeter, ...override }),
    [state, fetchMeters, addMeter, updateMeter, override]
  );

  return (
    <MetersContext.Provider value={context}>{children}</MetersContext.Provider>
  );
};

interface MetersKit {
  meters: Meter[];
  isLoading: boolean;
  error: Error | null;
  addMeter: (meters: Partial<Meter>) => Promise<void>;
}

let fetching = false;
export function useMeters(): MetersKit {
  const context = useContext(MetersContext);

  if (typeof context === 'undefined')
    throw new Error('useUsers must be used within a UsersProvider');

  const { fetchMeters, updateMeter, ...rest } = context;

  React.useEffect(() => {
    if (fetching || rest.isLoading || rest.meters !== null) return;

    fetching = true;
    fetchMeters().finally(() => (fetching = false));
  }, [fetchMeters, rest]);

  return rest;
}

interface MeterKit {
  user: Meter;
  updateMeter: (update: Partial<Meter>) => Promise<void>;
}

export function useMeter(id: string): MeterKit | null {
  const context = useContext(MetersContext);

  if (typeof context === 'undefined')
    throw new Error('useUser must be used within a UsersProvider');

  const { meters, updateMeter } = context;

  const meter = useMemo(() => meters.find(user => user.id === id), [
    id,
    meters
  ]);

  if (typeof meter === 'undefined') return null;

  return { user: meter, updateMeter: updateMeter.bind(undefined, meter.id) };
}
