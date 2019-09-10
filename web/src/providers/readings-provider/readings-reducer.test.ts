import { readingsReducer, ReadingsState } from './readings-reducer';
import * as action from './readings-actions';
import { buildList, buildReading } from '../../utils/fake-builder';

const initialState: ReadingsState = {
  isLoading: false,
  error: null,
  readings: []
};

test('returns state for unknown action', () => {
  /// @ts-ignore
  expect(readingsReducer(initialState, { type: 'TEST' })).toEqual(initialState);
});

test('handles fetch actions', () => {
  // Request
  const requestState = readingsReducer(
    initialState,
    action.fetchReadingsRequest()
  );
  expect(requestState).toEqual({
    ...initialState,
    isLoading: true
  });

  // Success
  const readings = buildList(buildReading);
  expect(
    readingsReducer(requestState, action.fetchReadingsSuccess(readings))
  ).toEqual({
    ...initialState,
    readings
  });

  // Failure
  const e = new Error('Test error');
  expect(readingsReducer(requestState, action.fetchReadingsFailure(e))).toEqual(
    {
      ...initialState,
      error: e
    }
  );
});

test('handles create actions', () => {
  // Request
  const requestState = readingsReducer(
    initialState,
    action.addReadingRequest()
  );
  expect(requestState).toEqual(initialState);

  // Success
  const reading = buildReading();
  expect(
    readingsReducer(requestState, action.addReadingSuccess(reading))
  ).toEqual({
    ...initialState,
    readings: [reading]
  });

  // Success with existing users
  const readings = buildList(buildReading);
  const existingState = readingsReducer(
    initialState,
    action.fetchReadingsSuccess(readings)
  );
  expect(
    readingsReducer(existingState, action.addReadingSuccess(reading))
  ).toEqual({
    ...initialState,
    readings: [...existingState.readings, reading]
  });

  // Failure
  const e = new Error('Test Error');
  expect(readingsReducer(requestState, action.addReadingFailure(e))).toEqual({
    ...initialState,
    error: e
  });
});

test('handles update actions', () => {
  const readingsTemp = buildList(buildReading, 10, 10);
  const updater = buildReading();
  const readings = [
    ...readingsTemp.slice(0, 5),
    updater,
    ...readingsTemp.slice(5, 11)
  ];
  const existingState = readingsReducer(
    initialState,
    action.fetchReadingsSuccess(readings)
  );

  // Request
  expect(
    readingsReducer(existingState, action.updateReadingRequest(updater.id))
  ).toEqual({
    ...existingState,
    readings: existingState.readings.map(r =>
      r.id === updater.id
        ? { ...updater, status: { ...updater.status, saving: true } }
        : r
    )
  });

  // Success
  const update = { ...updater, firstName: 'Hans', lastName: 'Peter' };
  expect(
    readingsReducer(existingState, action.updateReadingSuccess(update))
  ).toEqual({
    ...existingState,
    readings: existingState.readings.map(r =>
      r.id === update.id
        ? {
            ...update
          }
        : r
    )
  });

  // Failure
  const e = new Error('Test Error');
  expect(
    readingsReducer(existingState, action.updateReadingFailure(updater.id, e))
  ).toEqual({
    ...existingState,
    readings: existingState.readings.map(r =>
      r.id === updater.id
        ? {
            ...r,
            status: {
              ...r.status,
              saveError: e
            }
          }
        : r
    )
  });
});
