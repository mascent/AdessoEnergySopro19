import { isStringEmpty } from './validators';

test('is true for an empty string or only spaces', () => {
  expect(isStringEmpty('')).toBeTruthy();
  expect(isStringEmpty('        ')).toBeTruthy();
});

test('is false for a string with characters', () => {
  expect(isStringEmpty('djskdsjd')).toBeFalsy();
  expect(isStringEmpty('    djsjd  d   ')).toBeFalsy();
});

test('is false for a string of numbers', () => {
  expect(isStringEmpty('162173672')).toBeFalsy();
  expect(isStringEmpty('  1343  242   ')).toBeFalsy();
});
