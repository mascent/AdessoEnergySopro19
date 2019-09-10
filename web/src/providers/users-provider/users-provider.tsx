import React, { useReducer, useContext, useMemo, useCallback } from 'react';
import { User } from '../../typings/provider-data-interfaces';
import { usersReducer, UsersState } from './users-reducer';
import Logger from '../../services/logger/logger';
import {
  fetchUsersRequest,
  fetchUsersSuccess,
  fetchUsersFailure,
  addUserRequest,
  addUserSuccess,
  addUserFailure,
  updateUserRequest,
  updateUserFailure
} from './users-actions';
import { users } from '../../services/ad-api';
import { mapUserDtoToUser } from '../../lib/mappers';

interface UsersContext {
  users: User[];
  isLoading: boolean;
  error: Error | null;
  fetchUsers: () => Promise<void>;
  addUser: (user: Partial<User>) => Promise<void>;
  updateUser: (id: string, update: Partial<User>) => Promise<void>;
}

const initialContext: UsersState = {
  users: [],
  isLoading: false,
  error: null
};

const UsersContext = React.createContext<UsersContext | undefined>(undefined);

interface UsersProviderProps {
  override?: UsersContext;
}

export const UsersProvider: React.FC<UsersProviderProps> = ({
  override,
  children
}) => {
  const [state, dispatch] = useReducer(usersReducer, initialContext);

  const fetchUsers = useCallback(async () => {
    try {
      Logger.logBreadcrumb('info', 'users-context', 'Fetching users');
      dispatch(fetchUsersRequest());

      const result = await users.getAllUsers();
      Logger.logBreadcrumb('info', 'users-context', 'Fetched users');
      dispatch(fetchUsersSuccess(result.map(user => mapUserDtoToUser(user))));
    } catch (e) {
      Logger.logBreadcrumb('error', 'users-context', 'Fetch users failed');
      Logger.captureException(e);
      dispatch(fetchUsersFailure(e));
    }
  }, []);

  const addUser = useCallback(async (user: Partial<User>) => {
    try {
      Logger.logBreadcrumb('info', 'users-context', 'Adding user');
      dispatch(addUserRequest());

      const result = await users.createNewUser(user);
      Logger.logBreadcrumb('info', 'users-context', 'Added user');
      dispatch(addUserSuccess(mapUserDtoToUser(result)));
    } catch (e) {
      Logger.logBreadcrumb('error', 'users-context', 'Add user failed');
      Logger.captureException(e);
      dispatch(addUserFailure(e));
    }
  }, []);

  const updateUser = useCallback(async (id: string, update: Partial<User>) => {
    try {
      Logger.logBreadcrumb('info', 'users-context', 'Updating user');
      dispatch(updateUserRequest(id));
      // Do fetch
      Logger.logBreadcrumb('info', 'users-context', 'Updated user');
      // dispatch(addUserSuccess());
    } catch (e) {
      Logger.logBreadcrumb('error', 'users-context', 'Update user failed');
      Logger.captureException(e);
      dispatch(updateUserFailure(id, e));
    }
  }, []);

  // Override the internal state with a possible override for test purposes
  const context = useMemo(
    () => ({ ...state, fetchUsers, addUser, updateUser, ...override }),
    [state, fetchUsers, updateUser, addUser, override]
  );

  return (
    <UsersContext.Provider value={context}>{children}</UsersContext.Provider>
  );
};

interface UsersKit {
  users: User[];
  isLoading: boolean;
  error: Error | null;
  addUser: (user: Partial<User>) => Promise<void>;
}

let fetching = false;
export function useUsers(): UsersKit {
  const context = useContext(UsersContext);

  if (typeof context === 'undefined')
    throw new Error('useUsers must be used within a UsersProvider');

  const { fetchUsers, updateUser, ...rest } = context;

  React.useEffect(() => {
    if (fetching || rest.isLoading || rest.users !== null) return;

    fetching = true;
    fetchUsers().finally(() => (fetching = false));
  }, [fetchUsers, rest]);

  return rest;
}

interface UserKit {
  user: User;
  updateUser: (update: Partial<User>) => Promise<void>;
}

export function useUser(id: string): UserKit | null {
  const context = useContext(UsersContext);

  if (typeof context === 'undefined')
    throw new Error('useUser must be used within a UsersProvider');

  const { users, updateUser } = context;

  const user = useMemo(() => users.find(user => user.id === id), [id, users]);

  if (typeof user === 'undefined') return null;

  return { user, updateUser: updateUser.bind(undefined, user.id) };
}
