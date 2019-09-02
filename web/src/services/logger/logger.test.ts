import Logger from './logger';

// const OLD_ENV = process.env;

//   beforeEach(() => {
//     jest.resetModules() // this is important - it clears the cache
//     process.env = { ...OLD_ENV };
//   });

//   afterEach(() => {
//     process.env = OLD_ENV;
//   });

test.skip('logger does not log in production', () => {
  // We would have to change the env in order to check whether or not the
  // logger logs in prod. However every solution I find modifies the env like
  // this which might be possible but is not allowed (JS can, TS says no)
  // https://code-examples.net/en/q/2dcf031
  // TODO: Find a way to mock env vars with jest
  process.env.NODE_ENV = 'production';

  // This also does not work...
  // jest.spyOn(process, 'env').mockImplementation(() => ({NODE_ENV: 'production'}));

  expect(process.env).toBe('production');
});

test('logger logs a breadcrumb', () => {
  const group = jest
    .spyOn(global.console, 'groupCollapsed')
    .mockImplementation();
  const log = jest.spyOn(global.console, 'log').mockImplementation();

  Logger.logBreadcrumb('info', 'test', 'Test Message');

  expect(group).toBeCalledTimes(1);
  expect(group).toHaveBeenCalledWith(
    '%cLogger: Breadcrumb - %s : %s',
    'color: SeaGreen',
    'test',
    'Test Message'
  );

  expect(log).toHaveBeenCalledTimes(3);
  expect(log).nthCalledWith(1, 'Level: %c%s', 'font-weight: bold', 'info');
  expect(log).nthCalledWith(2, 'Category: %c%s', 'font-weight: bold', 'test');
  expect(log).nthCalledWith(
    3,
    'Message: %c%s',
    'font-weight: bold',
    'Test Message'
  );

  group.mockRestore();
  log.mockRestore();
});

test('logger logs an exception', () => {
  const group = jest
    .spyOn(global.console, 'groupCollapsed')
    .mockImplementation();
  const log = jest.spyOn(global.console, 'error').mockImplementation();

  const error = new Error('Test Error');

  Logger.captureException(error);

  expect(group).toBeCalledTimes(1);
  expect(group).toHaveBeenCalledWith(
    '%cLogger: Exception - %s',
    'color: Crimson',
    'Test Error'
  );
  expect(log).toHaveBeenCalledTimes(1);
  expect(log).toHaveBeenCalledWith(error);
});
