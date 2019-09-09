import { isStringEmpty, isValidEmail } from './validators';

describe('isStringEmpty function', () => {
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
});

describe('isEmail function', () => {
  test('is false for an empty email', () => {
    expect(isValidEmail('')).toBeFalsy();
    expect(isValidEmail('        ')).toBeFalsy();
  });

  const cases = [
    ['a@b.de', true],
    ['d', false],
    ['  a@b.de', false],
    ['a@b.d', false],
    ['hello@games.com', true]
  ];

  test.each(cases)('email %s is %s', (mail, result) => {
    /// @ts-ignore TS things both are string | boolean. Don't know why...
    expect(isValidEmail(mail)).toBe(result);
  });
});
