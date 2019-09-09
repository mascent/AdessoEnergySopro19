import { Reading } from '../../typings/provider-data-interfaces';

interface FetchReadingsRequestAction {
  type: 'FETCH_READINGS_REQUEST';
}

export function fetchReadingsRequest(): FetchReadingsRequestAction {
  return {
    type: 'FETCH_READINGS_REQUEST'
  };
}

interface FetchReadingsSuccessAction {
  type: 'FETCH_READINGS_SUCCESS';
  readings: Reading[];
}

export function fetchReadingsSuccess(
  readings: Reading[]
): FetchReadingsSuccessAction {
  return {
    type: 'FETCH_READINGS_SUCCESS',
    readings
  };
}

interface FetchReadingsFailureAction {
  type: 'FETCH_READINGS_FAILURE';
  error: Error;
}

export function fetchReadingsFailure(error: Error): FetchReadingsFailureAction {
  return {
    type: 'FETCH_READINGS_FAILURE',
    error
  };
}

interface AddReadingRequestAction {
  type: 'ADD_READING_REQUEST';
}

export function addReadingRequest(): AddReadingRequestAction {
  return {
    type: 'ADD_READING_REQUEST'
  };
}

interface AddReadingSuccessAction {
  type: 'ADD_READING_SUCCESS';
  reading: Reading;
}

export function addReadingSuccess(reading: Reading): AddReadingSuccessAction {
  return {
    type: 'ADD_READING_SUCCESS',
    reading
  };
}

interface AddReadingFailureAction {
  type: 'ADD_READING_FAILURE';
  error: Error;
}

export function addReadingFailure(error: Error): AddReadingFailureAction {
  return {
    type: 'ADD_READING_FAILURE',
    error
  };
}

interface UpdateReadingRequestAction {
  type: 'UPDATE_READING_REQUEST';
  id: string;
}

export function updateReadingRequest(id: string): UpdateReadingRequestAction {
  return {
    type: 'UPDATE_READING_REQUEST',
    id
  };
}

interface UpdateReadingSuccessAction {
  type: 'UPDATE_READING_SUCCESS';
  reading: Reading;
}

export function updateReadingSuccess(
  reading: Reading
): UpdateReadingSuccessAction {
  return {
    type: 'UPDATE_READING_SUCCESS',
    reading
  };
}

interface UpdateReadingFailureAction {
  type: 'UPDATE_READING_FAILURE';
  id: string;
  error: Error;
}

export function updateReadingFailure(
  id: string,
  error: Error
): UpdateReadingFailureAction {
  return {
    type: 'UPDATE_READING_FAILURE',
    id,
    error
  };
}

export type Action =
  | FetchReadingsRequestAction
  | FetchReadingsSuccessAction
  | FetchReadingsFailureAction
  | AddReadingRequestAction
  | AddReadingSuccessAction
  | AddReadingFailureAction
  | UpdateReadingRequestAction
  | UpdateReadingSuccessAction
  | UpdateReadingFailureAction;
