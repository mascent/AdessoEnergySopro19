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
  updateMeterFailure,
  addMeterSuccess,
  updateMeterSuccess
} from './meters-actions';
import { meters } from '../../services/ad-api';
import {
  mapMeterDtoToMeter,
  mapInternalMeterToMeterDTO
} from '../../lib/mappers';

interface MetersContext {
  meters: Meter[];
  isLoading: boolean;
  error: Error | null;
  fetchMeters: (userId: string) => Promise<void>;
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
  override?: Partial<MetersContext>;
}

export const MetersProvider: React.FC<MetersProviderProps> = ({
  override,
  children
}) => {
  const [state, dispatch] = useReducer(metersReducer, initialContext);

  const fetchMeters = useCallback(async (userId: string) => {
    try {
      Logger.logBreadcrumb('info', 'meters-context', 'Fetching meters');
      dispatch(fetchMetersRequest());

      const res = await meters.getMetersForUser(userId);
      Logger.logBreadcrumb('info', 'meters-context', 'Fetched meters');
      dispatch(fetchMetersSuccess(res.map(r => mapMeterDtoToMeter(r))));
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

      const res = await meters.createNewMeter(
        mapInternalMeterToMeterDTO(meter)
      );
      Logger.logBreadcrumb('info', 'meters-context', 'Added meter');
      dispatch(addMeterSuccess(mapMeterDtoToMeter(res)));
    } catch (e) {
      Logger.logBreadcrumb('error', 'meters-context', 'Add meter failed');
      Logger.captureException(e);
      dispatch(addMeterFailure(e));
    }
  }, []);

  const updateMeter = useCallback(
    async (id: string, update: Partial<Meter>) => {
      try {
        Logger.logBreadcrumb('info', 'meters-context', 'Updating meter');
        dispatch(updateMeterRequest(id));

        const res = await meters.updateMeter(
          id,
          mapInternalMeterToMeterDTO(update)
        );
        Logger.logBreadcrumb('info', 'meters-context', 'Updated meter');
        dispatch(updateMeterSuccess(mapMeterDtoToMeter(res)));
      } catch (e) {
        Logger.logBreadcrumb('error', 'meters-context', 'Update meter failed');
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
  addMeter: (meter: Partial<Meter>) => Promise<void>;
}

let fetching = false;
let lastFetched = '';
export function useMeters(userId: string): MetersKit {
  const context = useContext(MetersContext);

  if (typeof context === 'undefined')
    throw new Error('useMeters must be used within a MetersProvider');

  const { fetchMeters, updateMeter, ...rest } = context;

  React.useEffect(() => {
    if (
      fetching ||
      rest.isLoading ||
      (rest.meters.length !== 0 && userId === lastFetched)
    )
      return;

    fetching = true;
    lastFetched = userId;
    fetchMeters(userId).finally(() => (fetching = false));
  }, [fetchMeters, rest, userId]);

  return rest;
}

interface MeterKit {
  meter: Meter;
  updateMeter: (update: Partial<Meter>) => Promise<void>;
}

export function useMeter(id: string): MeterKit | null {
  const context = useContext(MetersContext);

  if (typeof context === 'undefined')
    throw new Error('useMeter must be used within a MetersProvider');

  const { meters, updateMeter } = context;

  const meter = useMemo(() => meters.find(user => user.id === id), [
    id,
    meters
  ]);

  if (typeof meter === 'undefined') return null;

  return { meter, updateMeter: updateMeter.bind(undefined, meter.id) };
}
