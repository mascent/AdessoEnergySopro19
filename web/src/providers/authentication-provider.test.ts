import { renderHook, act } from '@testing-library/react-hooks';
import { useAuth, AuthenticationProvider } from './authentication-provider';
import { config, auth } from '../services/ad-api';

test('login status is false at start', () => {
  const { result } = renderHook(() => useAuth(), {
    wrapper: AuthenticationProvider
  });

  expect(result.current.isLoggedIn).toBeFalsy();
  expect(result.current.userId).toBe('');
  expect(result.current.isAdmin).toBeFalsy();
});

test('calling useAuth without a Provider throws', () => {
  const { result } = renderHook(() => useAuth());

  expect(result.error.message).toBe(
    'useAuth must be used inside a AuthenticationProvider.'
  );
});

test('calling login gets the user logged in', async () => {
  const confMock = jest.spyOn(config, 'setToken');
  const logMock = jest.spyOn(auth, 'login').mockImplementation(async () => ({
    userId: '12345',
    isAdmin: true
  }));

  const { result } = renderHook(() => useAuth(), {
    wrapper: AuthenticationProvider
  });

  let success = true;
  await act(async () => {
    success = await result.current.login('testuser', 'passipass');
  });

  expect(confMock).toHaveBeenCalledTimes(1);
  expect(confMock).toBeCalledWith('testuser', 'passipass');
  expect(logMock).toHaveBeenCalledTimes(1);

  expect(success).toBeTruthy();
  expect(result.current.isLoggedIn).toBeTruthy();
  expect(result.current.userId).toBe('12345');
  expect(result.current.isAdmin).toBeTruthy();

  confMock.mockRestore();
  logMock.mockRestore();
});

test('login returns false if username or password is wrong', async () => {
  const confMock = jest.spyOn(config, 'setToken');
  const logMock = jest.spyOn(auth, 'login').mockImplementation(async () => {
    throw new Error('A problem occurred with the request.');
  });

  const { result } = renderHook(() => useAuth(), {
    wrapper: AuthenticationProvider
  });

  let success = true;
  await act(async () => {
    success = await result.current.login('testuser', 'passipass');
  });

  expect(success).toBeFalsy();
  expect(result.current.isLoggedIn).toBeFalsy();
  expect(result.current.userId).toBe('');
  expect(result.current.isAdmin).toBeFalsy();

  confMock.mockRestore();
  logMock.mockRestore();
});

test('logout resets the authentication provider and api config', async () => {
  const confMock = jest.spyOn(config, 'resetToken');
  const logMock = jest.spyOn(auth, 'login').mockImplementation(async () => ({
    userId: '12345',
    isAdmin: true
  }));

  const { result } = renderHook(() => useAuth(), {
    wrapper: AuthenticationProvider
  });

  await act(async () => {
    result.current.login('testuser', 'passipass');
  });

  // At this point we are logged in...
  expect(result.current.isLoggedIn).toBeTruthy();

  // ...so let's log out again
  await act(async () => {
    result.current.logout();
  });

  expect(confMock).toHaveBeenCalledTimes(1);
  expect(result.current.isLoggedIn).toBeFalsy();
  expect(result.current.userId).toBe('');
  expect(result.current.isAdmin).toBeFalsy();

  confMock.mockRestore();
  logMock.mockRestore();
});
