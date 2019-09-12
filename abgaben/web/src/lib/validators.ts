import isEmail from 'validator/lib/isEmail';

export function isStringEmpty(str: string) {
  return str.trim() === '';
}

export function isValidEmail(email: string) {
  return isEmail(email);
}

export function isNumber(num: string) {
  return !isNaN(parseInt(num, 10));
}

export function isMeterTypeValid(type: string) {
  return type === 'Gas' || type === 'Water' || type === 'Electricity';
}
