import { Reading } from '../../typings/provider-data-interfaces';
import { Action } from './readings-actions';

export interface ReadingsState {
  readings: Reading[];
  isLoading: boolean;
  error: Error | null;
}

export function readingsReducer(
  state: ReadingsState,
  action: Action
): ReadingsState {
  switch (action.type) {
    case 'FETCH_READINGS_REQUEST':
      return { ...state, isLoading: true, error: null };
    case 'FETCH_READINGS_SUCCESS':
      return { ...state, isLoading: false, readings: action.readings };
    case 'FETCH_READINGS_FAILURE':
      return { ...state, isLoading: false, error: action.error };
    case 'ADD_READING_REQUEST':
      return { ...state, error: null };
    case 'ADD_READING_SUCCESS':
      return {
        ...state,
        isLoading: false,
        readings: [...state.readings, action.reading]
      };
    case 'ADD_READING_FAILURE':
      return { ...state, isLoading: false, error: action.error };
    case 'UPDATE_READING_REQUEST':
      return {
        ...state,
        readings: state.readings.map(reading =>
          reading.id === action.id
            ? {
                ...reading,
                status: { ...reading.status, saving: true }
              }
            : reading
        )
      };
    case 'UPDATE_READING_SUCCESS':
      return {
        ...state,
        readings: state.readings.map(reading =>
          reading.id === action.reading.id
            ? {
                ...action.reading
              }
            : reading
        )
      };
    case 'UPDATE_READING_FAILURE':
      return {
        ...state,
        readings: state.readings.map(reading =>
          reading.id === action.id
            ? {
                ...reading,
                status: {
                  ...reading.status,
                  saving: false,
                  saveError: action.error
                }
              }
            : reading
        )
      };
    default:
      return state;
  }
}
