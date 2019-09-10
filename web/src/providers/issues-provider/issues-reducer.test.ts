import { issuesReducer, IssuesState } from './issues-reducer';
import * as action from './issues-actions';
import { buildList, buildIssue } from '../../utils/fake-builder';

const initialState: IssuesState = {
  isLoading: false,
  error: null,
  issues: []
};

test('handles fetch actions', () => {
  // Request
  const requestState = issuesReducer(initialState, action.fetchIssuesRequest());
  expect(requestState).toEqual({
    ...initialState,
    isLoading: true
  });

  // Success
  const issues = buildList(buildIssue);
  expect(
    issuesReducer(requestState, action.fetchIssuesSuccess(issues))
  ).toEqual({
    ...initialState,
    issues
  });

  // Failure
  const e = new Error('Test error');
  expect(issuesReducer(requestState, action.fetchIssuesFailure(e))).toEqual({
    ...initialState,
    error: e
  });
});

test('handles create actions', () => {
  // Request
  const requestState = issuesReducer(initialState, action.addIssueRequest());
  expect(requestState).toEqual(initialState);

  // Success
  const issue = buildIssue();
  expect(issuesReducer(requestState, action.addIssueSuccess(issue))).toEqual({
    ...initialState,
    issues: [issue]
  });

  // Success with existing users
  const issues = buildList(buildIssue);
  const existingState = issuesReducer(
    initialState,
    action.fetchIssuesSuccess(issues)
  );
  expect(issuesReducer(existingState, action.addIssueSuccess(issue))).toEqual({
    ...initialState,
    issues: [...existingState.issues, issue]
  });

  // Failure
  const e = new Error('Test Error');
  expect(issuesReducer(requestState, action.addIssueFailure(e))).toEqual({
    ...initialState,
    error: e
  });
});

test('handles update actions', () => {
  const issuesTemp = buildList(buildIssue, 10, 10);
  const updater = buildIssue();
  const issues = [
    ...issuesTemp.slice(0, 5),
    updater,
    ...issuesTemp.slice(5, 11)
  ];
  const existingState = issuesReducer(
    initialState,
    action.fetchIssuesSuccess(issues)
  );

  // Request
  expect(
    issuesReducer(existingState, action.updateIssueRequest(updater.id))
  ).toEqual({
    ...existingState,
    issues: existingState.issues.map(i =>
      i.id === updater.id
        ? { ...updater, status: { ...updater.status, saving: true } }
        : i
    )
  });

  // Success
  const update = { ...updater, firstName: 'Hans', lastName: 'Peter' };
  expect(
    issuesReducer(existingState, action.updateIssueSuccess(update))
  ).toEqual({
    ...existingState,
    issues: existingState.issues.map(i =>
      i.id === update.id
        ? {
            ...update
          }
        : i
    )
  });

  // Failure
  const e = new Error('Test Error');
  expect(
    issuesReducer(existingState, action.updateIssueFailure(updater.id, e))
  ).toEqual({
    ...existingState,
    issues: existingState.issues.map(i =>
      i.id === updater.id
        ? {
            ...i,
            status: {
              ...i.status,
              saveError: e
            }
          }
        : i
    )
  });
});
