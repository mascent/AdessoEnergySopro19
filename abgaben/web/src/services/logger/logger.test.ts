import Logger from './logger';

describe('Logger', () => {
  const OLD_ENV = process.env;

  beforeEach(() => {
    jest.resetModules(); // this is important - it clears the cache
    process.env = { ...OLD_ENV };
  });

  afterEach(() => {
    process.env = OLD_ENV;
  });

  test('logger does not log in production', () => {
    /// @ts-ignore
    process.env.NODE_ENV = 'production';
    const log = jest.spyOn(global.console, 'log').mockImplementation();
    const error = jest.spyOn(global.console, 'error').mockImplementation();

    Logger.logBreadcrumb('info', 'test', 'Test Message');
    Logger.captureException(new Error('Test'));

    expect(log).toBeCalledTimes(0);
    expect(error).toBeCalledTimes(0);

    log.mockRestore();
    error.mockRestore();
  });

  test('logger does not log in testing', () => {
    /// @ts-ignore
    process.env.NODE_ENV = 'testing';
    const log = jest.spyOn(global.console, 'log').mockImplementation();
    const error = jest.spyOn(global.console, 'error').mockImplementation();

    Logger.logBreadcrumb('info', 'test', 'Test Message');
    Logger.captureException(new Error('Test'));

    expect(log).toBeCalledTimes(0);
    expect(error).toBeCalledTimes(0);

    log.mockRestore();
    error.mockRestore();
  });

  test('logger logs a breadcrumb', () => {
    /// @ts-ignore
    process.env.NODE_ENV = 'development';

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
    /// @ts-ignore
    process.env.NODE_ENV = 'development';

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
});
