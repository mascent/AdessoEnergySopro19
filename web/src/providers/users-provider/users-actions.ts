import { User } from '../../typings/provider-data-interfaces';

interface FetchUsersRequestAction {
  type: 'FETCH_USERS_REQUEST';
}

export function fetchUsersRequest(): FetchUsersRequestAction {
  return {
    type: 'FETCH_USERS_REQUEST'
  };
}

interface FetchUsersSuccessAction {
  type: 'FETCH_USERS_SUCCESS';
  users: User[];
}

export function fetchUsersSuccess(users: User[]): FetchUsersSuccessAction {
  return {
    type: 'FETCH_USERS_SUCCESS',
    users
  };
}

interface FetchUsersFailureAction {
  type: 'FETCH_USERS_FAILURE';
  error: Error;
}

export function fetchUsersFailure(error: Error): FetchUsersFailureAction {
  return {
    type: 'FETCH_USERS_FAILURE',
    error
  };
}

interface AddUserRequestAction {
  type: 'ADD_USER_REQUEST';
}

export function addUserRequest(): AddUserRequestAction {
  return {
    type: 'ADD_USER_REQUEST'
  };
}

interface AddUserSuccessAction {
  type: 'ADD_USER_SUCCESS';
  user: User;
}

export function addUserSuccess(user: User): AddUserSuccessAction {
  return {
    type: 'ADD_USER_SUCCESS',
    user
  };
}

interface AddUserFailureAction {
  type: 'ADD_USER_FAILURE';
  error: Error;
}

export function addUserFailure(error: Error): AddUserFailureAction {
  return {
    type: 'ADD_USER_FAILURE',
    error
  };
}

interface UpdateUserRequestAction {
  type: 'UPDATE_USER_REQUEST';
  id: string;
}

export function updateUserRequest(id: string): UpdateUserRequestAction {
  return {
    type: 'UPDATE_USER_REQUEST',
    id
  };
}

interface UpdateUserSuccessAction {
  type: 'UPDATE_USER_SUCCESS';
  user: User;
}

export function updateUserSuccess(user: User): UpdateUserSuccessAction {
  return {
    type: 'UPDATE_USER_SUCCESS',
    user
  };
}

interface UpdateUserFailureAction {
  type: 'UPDATE_USER_FAILURE';
  id: string;
  error: Error;
}

export function updateUserFailure(
  id: string,
  error: Error
): UpdateUserFailureAction {
  return {
    type: 'UPDATE_USER_FAILURE',
    id,
    error
  };
}

export type Action =
  | FetchUsersRequestAction
  | FetchUsersSuccessAction
  | FetchUsersFailureAction
  | AddUserRequestAction
  | AddUserSuccessAction
  | AddUserFailureAction
  | UpdateUserRequestAction
  | UpdateUserSuccessAction
  | UpdateUserFailureAction;
