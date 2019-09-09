import { Meter } from '../../typings/provider-data-interfaces';

interface FetchMetersRequestAction {
  type: 'FETCH_METERS_REQUEST';
}

export function fetchMetersRequest(): FetchMetersRequestAction {
  return {
    type: 'FETCH_METERS_REQUEST'
  };
}

interface FetchMetersSuccessAction {
  type: 'FETCH_METERS_SUCCESS';
  meters: Meter[];
}

export function fetchMetersSuccess(meters: Meter[]): FetchMetersSuccessAction {
  return {
    type: 'FETCH_METERS_SUCCESS',
    meters
  };
}

interface FetchMetersFailureAction {
  type: 'FETCH_METERS_FAILURE';
  error: Error;
}

export function fetchMetersFailure(error: Error): FetchMetersFailureAction {
  return {
    type: 'FETCH_METERS_FAILURE',
    error
  };
}

interface AddMeterRequestAction {
  type: 'ADD_METER_REQUEST';
}

export function addMeterRequest(): AddMeterRequestAction {
  return {
    type: 'ADD_METER_REQUEST'
  };
}

interface AddMeterSuccessAction {
  type: 'ADD_METER_SUCCESS';
  meter: Meter;
}

export function addMeterSuccess(meter: Meter): AddMeterSuccessAction {
  return {
    type: 'ADD_METER_SUCCESS',
    meter
  };
}

interface AddMeterFailureAction {
  type: 'ADD_METER_FAILURE';
  error: Error;
}

export function addMeterFailure(error: Error): AddMeterFailureAction {
  return {
    type: 'ADD_METER_FAILURE',
    error
  };
}

interface UpdateMeterRequestAction {
  type: 'UPDATE_METER_REQUEST';
  id: string;
}

export function updateMeterRequest(id: string): UpdateMeterRequestAction {
  return {
    type: 'UPDATE_METER_REQUEST',
    id
  };
}

interface UpdateUserSuccessAction {
  type: 'UPDATE_METER_SUCCESS';
  meter: Meter;
}

export function updateMeterSuccess(meter: Meter): UpdateUserSuccessAction {
  return {
    type: 'UPDATE_METER_SUCCESS',
    meter
  };
}

interface UpdateMeterFailureAction {
  type: 'UPDATE_METER_FAILURE';
  id: string;
  error: Error;
}

export function updateMeterFailure(
  id: string,
  error: Error
): UpdateMeterFailureAction {
  return {
    type: 'UPDATE_METER_FAILURE',
    id,
    error
  };
}

export type Action =
  | FetchMetersRequestAction
  | FetchMetersSuccessAction
  | FetchMetersFailureAction
  | AddMeterRequestAction
  | AddMeterSuccessAction
  | AddMeterFailureAction
  | UpdateMeterRequestAction
  | UpdateUserSuccessAction
  | UpdateMeterFailureAction;
