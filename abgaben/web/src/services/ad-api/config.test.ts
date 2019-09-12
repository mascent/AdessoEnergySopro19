import ApiConfig from './config';

test('accessing baseUrl after initiation does not throw', async () => {
  const config = new ApiConfig();
  config.init({ baseUrl: 'https://adesso.energy:8079' });
  expect(config.baseUrl).toBe('https://adesso.energy:8079');
});

test('accessing baseUrl before initiation throws', async () => {
  const config = new ApiConfig();
  expect(() => config.baseUrl).toThrow();
});

test('calling init twice will throw', async () => {
  const config = new ApiConfig();
  config.init({ baseUrl: 'https://adesso.energy:8079' });
  const secondCall = () => config.init({ baseUrl: '...' });
  expect(secondCall).toThrow();
});

test('accessing token before setting it throws', async () => {
  const config = new ApiConfig();
  expect(() => config.token).toThrow();
});

test('accessing token after setting it does not throw', async () => {
  const config = new ApiConfig();
  config.setToken('username', 'password');
  expect(config.token).toBe(btoa('username:password'));
});

test('setting token as empty string throws', async () => {
  const config = new ApiConfig();
  expect(() => config.setToken('', ' ')).toThrow();
});

test('token can be set multiple times', async () => {
  const config = new ApiConfig();

  config.setToken('firstToken', 'password');
  expect(config.token).toBe(btoa('firstToken:password'));

  config.setToken('secondToken', 'password');
  expect(config.token).toBe(btoa('secondToken:password'));

  config.setToken('thirdToken', 'password');
  expect(config.token).toBe(btoa('thirdToken:password'));
});
