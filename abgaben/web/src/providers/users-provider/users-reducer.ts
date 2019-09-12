import { User } from '../../typings/provider-data-interfaces';
import { Action } from './users-actions';

export interface UsersState {
  users: User[] | null;
  isLoading: boolean;
  error: Error | null;
}

export function usersReducer(state: UsersState, action: Action): UsersState {
  switch (action.type) {
    case 'FETCH_USERS_REQUEST':
      return { ...state, isLoading: true, error: null, users: null };
    case 'FETCH_USERS_SUCCESS':
      return { ...state, isLoading: false, users: action.users };
    case 'FETCH_USERS_FAILURE':
      return { ...state, isLoading: false, error: action.error };
    case 'ADD_USER_REQUEST':
      return { ...state, error: null };
    case 'ADD_USER_SUCCESS':
      return {
        ...state,
        isLoading: false,
        users: !state.users ? [action.user] : [action.user, ...state.users]
      };
    case 'ADD_USER_FAILURE':
      return { ...state, isLoading: false, error: action.error };
    case 'UPDATE_USER_REQUEST': {
      if (!state.users) return state;
      return {
        ...state,
        users: state.users.map(user =>
          user.id === action.id
            ? {
                ...user,
                status: { ...user.status, saving: true }
              }
            : user
        )
      };
    }
    case 'UPDATE_USER_SUCCESS': {
      if (!state.users) return state;
      return {
        ...state,
        users: state.users.map(user =>
          user.id === action.user.id
            ? {
                ...action.user
              }
            : user
        )
      };
    }
    case 'UPDATE_USER_FAILURE': {
      if (!state.users) return state;
      return {
        ...state,
        users: state.users.map(user =>
          user.id === action.id
            ? {
                ...user,
                status: {
                  ...user.status,
                  saving: false,
                  saveError: action.error
                }
              }
            : user
        )
      };
    }
    default:
      return state;
  }
}
