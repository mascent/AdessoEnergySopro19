import { usersReducer, UsersState } from './users-reducer';
import * as action from './users-actions';
import { buildList, buildUser } from '../../utils/fake-builder';

const initialState: UsersState = {
  isLoading: false,
  error: null,
  users: null
};

test('returns state for unknown action', () => {
  /// @ts-ignore
  expect(usersReducer(initialState, { type: 'TEST' })).toEqual(initialState);
});

test('handles fetch actions', () => {
  // Request
  const requestState = usersReducer(initialState, action.fetchUsersRequest());
  expect(requestState).toEqual({
    ...initialState,
    isLoading: true
  });

  // Success
  const users = buildList(buildUser);
  expect(usersReducer(requestState, action.fetchUsersSuccess(users))).toEqual({
    ...initialState,
    users
  });

  // Failure
  const e = new Error('Test error');
  expect(usersReducer(requestState, action.fetchUsersFailure(e))).toEqual({
    ...initialState,
    error: e
  });
});

test('handles create actions', () => {
  // Request
  const requestState = usersReducer(initialState, action.addUserRequest());
  expect(requestState).toEqual(initialState);

  // Success
  const user = buildUser();
  expect(usersReducer(requestState, action.addUserSuccess(user))).toEqual({
    ...initialState,
    users: [user]
  });

  // Success with existing users
  const users = buildList(buildUser);
  const existingState = usersReducer(
    initialState,
    action.fetchUsersSuccess(users)
  );
  expect(usersReducer(existingState, action.addUserSuccess(user))).toEqual({
    ...initialState,
    users: [...existingState.users, user]
  });

  // Failure
  const e = new Error('Test Error');
  expect(usersReducer(requestState, action.addUserFailure(e))).toEqual({
    ...initialState,
    error: e
  });
});

test('handles update actions', () => {
  const usersTemp = buildList(buildUser, 10, 10);
  const updater = buildUser();
  const users = [...usersTemp.slice(0, 5), updater, ...usersTemp.slice(5, 11)];
  const existingState = usersReducer(
    initialState,
    action.fetchUsersSuccess(users)
  );

  // Request
  expect(
    usersReducer(existingState, action.updateUserRequest(updater.id))
  ).toEqual({
    ...existingState,
    users: existingState.users.map(u =>
      u.id === updater.id
        ? { ...updater, status: { ...updater.status, saving: true } }
        : u
    )
  });

  // Success
  const update = { ...updater, firstName: 'Hans', lastName: 'Peter' };
  expect(usersReducer(existingState, action.updateUserSuccess(update))).toEqual(
    {
      ...existingState,
      users: existingState.users.map(user =>
        user.id === update.id
          ? {
              ...update
            }
          : user
      )
    }
  );

  // Failure
  const e = new Error('Test Error');
  expect(
    usersReducer(existingState, action.updateUserFailure(updater.id, e))
  ).toEqual({
    ...existingState,
    users: existingState.users.map(user =>
      user.id === updater.id
        ? {
            ...user,
            status: {
              ...user.status,
              saveError: e
            }
          }
        : user
    )
  });
});
