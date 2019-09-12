import { Issue } from '../../typings/provider-data-interfaces';

interface FetchIssuesRequestAction {
  type: 'FETCH_ISSUES_REQUEST';
}

export function fetchIssuesRequest(): FetchIssuesRequestAction {
  return {
    type: 'FETCH_ISSUES_REQUEST'
  };
}

interface FetchIssuesSuccessAction {
  type: 'FETCH_ISSUES_SUCCESS';
  issues: Issue[];
}

export function fetchIssuesSuccess(issues: Issue[]): FetchIssuesSuccessAction {
  return {
    type: 'FETCH_ISSUES_SUCCESS',
    issues
  };
}

interface FetchIssuesFailureAction {
  type: 'FETCH_ISSUES_FAILURE';
  error: Error;
}

export function fetchIssuesFailure(error: Error): FetchIssuesFailureAction {
  return {
    type: 'FETCH_ISSUES_FAILURE',
    error
  };
}

interface AddIssueRequestAction {
  type: 'ADD_ISSUE_REQUEST';
}

export function addIssueRequest(): AddIssueRequestAction {
  return {
    type: 'ADD_ISSUE_REQUEST'
  };
}

interface AddIssueSuccessAction {
  type: 'ADD_ISSUE_SUCCESS';
  issue: Issue;
}

export function addIssueSuccess(issue: Issue): AddIssueSuccessAction {
  return {
    type: 'ADD_ISSUE_SUCCESS',
    issue
  };
}

interface AddIssueFailureAction {
  type: 'ADD_ISSUE_FAILURE';
  error: Error;
}

export function addIssueFailure(error: Error): AddIssueFailureAction {
  return {
    type: 'ADD_ISSUE_FAILURE',
    error
  };
}

interface UpdateIssueRequestAction {
  type: 'UPDATE_ISSUE_REQUEST';
  id: string;
}

export function updateIssueRequest(id: string): UpdateIssueRequestAction {
  return {
    type: 'UPDATE_ISSUE_REQUEST',
    id
  };
}

interface UpdateIssueSuccessAction {
  type: 'UPDATE_ISSUE_SUCCESS';
  issue: Issue;
}

export function updateIssueSuccess(issue: Issue): UpdateIssueSuccessAction {
  return {
    type: 'UPDATE_ISSUE_SUCCESS',
    issue
  };
}

interface UpdateIssueFailureAction {
  type: 'UPDATE_ISSUE_FAILURE';
  id: string;
  error: Error;
}

export function updateIssueFailure(
  id: string,
  error: Error
): UpdateIssueFailureAction {
  return {
    type: 'UPDATE_ISSUE_FAILURE',
    id,
    error
  };
}

export type Action =
  | FetchIssuesRequestAction
  | FetchIssuesSuccessAction
  | FetchIssuesFailureAction
  | AddIssueRequestAction
  | AddIssueSuccessAction
  | AddIssueFailureAction
  | UpdateIssueRequestAction
  | UpdateIssueSuccessAction
  | UpdateIssueFailureAction;
