import { getApiBaseDomain } from './app-config';

describe('App config', () => {
  const OLD_ENV = process.env;

  beforeEach(() => {
    jest.resetModules(); // this is important - it clears the cache
    process.env = { ...OLD_ENV };
  });

  afterEach(() => {
    process.env = OLD_ENV;
  });

  test('getApiBaseDomain reads the api base from the environment', () => {
    process.env.REACT_APP_API_BASE_DOMAIN = 'https://adesso.energy';
    expect(getApiBaseDomain()).toEqual('https://adesso.energy');

    process.env.REACT_APP_API_BASE_DOMAIN = 'hallo';
    expect(getApiBaseDomain()).toEqual('hallo');
  });

  test('getApiBaseDomain throws if no environment var is set', () => {
    expect(getApiBaseDomain).toThrow();
  });
});
