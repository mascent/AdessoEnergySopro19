import { Issue } from '../../typings/provider-data-interfaces';
import { Action } from './issues-actions';

export interface IssuesState {
  issues: Issue[];
  isLoading: boolean;
  error: Error | null;
}

export function issuesReducer(state: IssuesState, action: Action): IssuesState {
  switch (action.type) {
    case 'FETCH_ISSUES_REQUEST':
      return { ...state, isLoading: true, error: null };
    case 'FETCH_ISSUES_SUCCESS':
      return { ...state, isLoading: false, issues: action.issues };
    case 'FETCH_ISSUES_FAILURE':
      return { ...state, isLoading: false, error: action.error };
    case 'ADD_ISSUE_REQUEST':
      return { ...state, error: null };
    case 'ADD_ISSUE_SUCCESS':
      return {
        ...state,
        isLoading: false,
        issues: [...state.issues, action.issue]
      };
    case 'ADD_ISSUE_FAILURE':
      return { ...state, isLoading: false, error: action.error };
    case 'UPDATE_ISSUE_REQUEST':
      return {
        ...state,
        issues: state.issues.map(issue =>
          issue.id === action.id
            ? {
                ...issue,
                status: { ...issue.status, saving: true }
              }
            : issue
        )
      };
    case 'UPDATE_ISSUE_SUCCESS':
      return {
        ...state,
        issues: state.issues.map(issue =>
          issue.id === action.issue.id
            ? {
                ...action.issue
              }
            : issue
        )
      };
    case 'UPDATE_ISSUE_FAILURE':
      return {
        ...state,
        issues: state.issues.map(issue =>
          issue.id === action.id
            ? {
                ...issue,
                status: {
                  ...issue.status,
                  saving: false,
                  saveError: action.error
                }
              }
            : issue
        )
      };
    default:
      return state;
  }
}
