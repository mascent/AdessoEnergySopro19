import { Meter } from '../../typings/provider-data-interfaces';
import { Action } from './meters-actions';

export interface MetersState {
  meters: Meter[] | null;
  isLoading: boolean;
  error: Error | null;
}

export function metersReducer(state: MetersState, action: Action): MetersState {
  switch (action.type) {
    case 'FETCH_METERS_REQUEST':
      return { ...state, isLoading: true, error: null, meters: null };
    case 'FETCH_METERS_SUCCESS':
      return { ...state, isLoading: false, meters: action.meters };
    case 'FETCH_METERS_FAILURE':
      return { ...state, isLoading: false, error: action.error };
    case 'ADD_METER_REQUEST':
      return { ...state, error: null };
    case 'ADD_METER_SUCCESS':
      return {
        ...state,
        isLoading: false,
        meters: state.meters ? [...state.meters, action.meter] : [action.meter]
      };
    case 'ADD_METER_FAILURE':
      return { ...state, isLoading: false, error: action.error };
    case 'UPDATE_METER_REQUEST': {
      if (!state.meters) return state;
      return {
        ...state,
        meters: state.meters.map(meter =>
          meter.id === action.id
            ? {
                ...meter,
                status: { ...meter.status, saving: true }
              }
            : meter
        )
      };
    }
    case 'UPDATE_METER_SUCCESS': {
      if (!state.meters) return state;
      return {
        ...state,
        meters: state.meters.map(meter =>
          meter.id === action.meter.id
            ? {
                ...action.meter
              }
            : meter
        )
      };
    }
    case 'UPDATE_METER_FAILURE': {
      if (!state.meters) return state;
      return {
        ...state,
        meters: state.meters.map(meter =>
          meter.id === action.id
            ? {
                ...meter,
                status: {
                  ...meter.status,
                  saving: false,
                  saveError: action.error
                }
              }
            : meter
        )
      };
    }
    default:
      return state;
  }
}
