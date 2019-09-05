import { User } from '../../typings/provider-data-interfaces';
import { Action } from './users-actions';

interface State {
  users: User[];
  isLoading: boolean;
  error: Error | null;
}

export function usersReducer(state: State, action: Action): State {
  switch (action.type) {
    case 'FETCH_USERS_REQUEST':
      return { ...state, isLoading: true, error: null };
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
        users: [...state.users, action.user]
      };
    case 'ADD_USER_FAILURE':
      return { ...state, isLoading: false, error: action.error };
    case 'UPDATE_USER_REQUEST':
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
    case 'UPDATE_USER_SUCCESS':
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
    case 'UPDATE_USER_FAILURE':
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
    default:
      return state;
  }
}
