import React, { useReducer, useContext, useMemo, useCallback } from 'react';
import { User } from '../../typings/provider-data-interfaces';
import { usersReducer } from './users-reducer';
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

interface UsersContext {
  users: User[];
  isLoading: boolean;
  error: Error | null;
}

const initialContext: UsersContext = {
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
      // Do fetch
      Logger.logBreadcrumb('info', 'users-context', 'Fetched users');
      dispatch(fetchUsersSuccess([]));
    } catch (e) {
      Logger.logBreadcrumb('error', 'users-context', 'Fetch users failed');
      Logger.captureException(e);
      dispatch(fetchUsersFailure(e));
    }
  }, []);

  const addUser = useCallback(async () => {
    try {
      Logger.logBreadcrumb('info', 'users-context', 'Adding user');
      dispatch(addUserRequest());
      // Do fetch
      Logger.logBreadcrumb('info', 'users-context', 'Added user');
      // dispatch(addUserSuccess());
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
  const context = useMemo(() => ({ ...state, ...override }), [state, override]);

  return (
    <UsersContext.Provider value={context}>{children}</UsersContext.Provider>
  );
};

export function useUsers() {
  const context = useContext(UsersContext);

  if (typeof context === 'undefined')
    throw new Error('useUsers must be used within a UsersProvider');
}
