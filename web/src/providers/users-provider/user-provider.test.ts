import { renderHook } from '@testing-library/react-hooks';
import { useUsers, UsersProvider, useUser } from './users-provider';
import { users } from '../../services/ad-api';
import { buildList, buildUserDTO } from '../../utils/fake-builder';
import { mapUserDtoToUser } from '../../lib/mappers';

test('useUsers throws if not rendered in his provider', () => {
  const { result } = renderHook(() => useUsers());

  expect(result.error.message).toBe(
    'useUsers must be used within a UsersProvider'
  );
});

test('useUser throws if not rendered in his provider', () => {
  const { result } = renderHook(() => useUser('123'));

  expect(result.error.message).toBe(
    'useUser must be used within a UsersProvider'
  );
});

test('userUsers triggers a fetch if no data is available', async () => {
  const userList = buildList(buildUserDTO);
  const fetch = jest
    .spyOn(users, 'getAllUsers')
    .mockImplementationOnce(async () => userList);

  const { result, waitForNextUpdate } = renderHook(() => useUsers(), {
    wrapper: UsersProvider
  });

  await waitForNextUpdate();

  expect(fetch).toHaveBeenCalledTimes(1);

  expect(result.current.users).toEqual(userList.map(u => mapUserDtoToUser(u)));
});
