import isEmail from 'validator/lib/isEmail';

export function isStringEmpty(str: string) {
  return str.trim() === '';
}

export function validateEmail(email: string) {
  return isEmail(email);
}
