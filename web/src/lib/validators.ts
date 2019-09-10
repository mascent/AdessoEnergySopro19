import isEmail from 'validator/lib/isEmail';

export function isStringEmpty(str: string) {
  return str.trim() === '';
}

export function isValidEmail(email: string) {
  return isEmail(email);
}

export function isMeterTypeValid(type: string) {
  return type === 'gas' || type === 'water' || type === 'electricity';
}
