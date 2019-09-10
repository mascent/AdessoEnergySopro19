import { metersReducer, MetersState } from './meters-reducer';
import * as action from './meters-actions';
import { buildList, buildMeter } from '../../utils/fake-builder';

const initialState: MetersState = {
  isLoading: false,
  error: null,
  meters: []
};

test('handles fetch actions', () => {
  // Request
  const requestState = metersReducer(initialState, action.fetchMetersRequest());
  expect(requestState).toEqual({
    ...initialState,
    isLoading: true
  });

  // Success
  const meters = buildList(buildMeter);
  expect(
    metersReducer(requestState, action.fetchMetersSuccess(meters))
  ).toEqual({
    ...initialState,
    meters
  });

  // Failure
  const e = new Error('Test error');
  expect(metersReducer(requestState, action.fetchMetersFailure(e))).toEqual({
    ...initialState,
    error: e
  });
});

test('handles create actions', () => {
  // Request
  const requestState = metersReducer(initialState, action.addMeterRequest());
  expect(requestState).toEqual(initialState);

  // Success
  const meter = buildMeter();
  expect(metersReducer(requestState, action.addMeterSuccess(meter))).toEqual({
    ...initialState,
    meters: [meter]
  });

  // Success with existing users
  const meters = buildList(buildMeter);
  const existingState = metersReducer(
    initialState,
    action.fetchMetersSuccess(meters)
  );
  expect(metersReducer(existingState, action.addMeterSuccess(meter))).toEqual({
    ...initialState,
    meters: [...existingState.meters, meter]
  });

  // Failure
  const e = new Error('Test Error');
  expect(metersReducer(requestState, action.addMeterFailure(e))).toEqual({
    ...initialState,
    error: e
  });
});

test('handles update actions', () => {
  const metersTemp = buildList(buildMeter, 10, 10);
  const updater = buildMeter();
  const meters = [
    ...metersTemp.slice(0, 5),
    updater,
    ...metersTemp.slice(5, 11)
  ];
  const existingState = metersReducer(
    initialState,
    action.fetchMetersSuccess(meters)
  );

  // Request
  expect(
    metersReducer(existingState, action.updateMeterRequest(updater.id))
  ).toEqual({
    ...existingState,
    meters: existingState.meters.map(m =>
      m.id === updater.id
        ? { ...updater, status: { ...updater.status, saving: true } }
        : m
    )
  });

  // Success
  const update = { ...updater, firstName: 'Hans', lastName: 'Peter' };
  expect(
    metersReducer(existingState, action.updateMeterSuccess(update))
  ).toEqual({
    ...existingState,
    meters: existingState.meters.map(m =>
      m.id === update.id
        ? {
            ...update
          }
        : m
    )
  });

  // Failure
  const e = new Error('Test Error');
  expect(
    metersReducer(existingState, action.updateMeterFailure(updater.id, e))
  ).toEqual({
    ...existingState,
    meters: existingState.meters.map(m =>
      m.id === updater.id
        ? {
            ...m,
            status: {
              ...m.status,
              saveError: e
            }
          }
        : m
    )
  });
});
