import { renderHook, act } from '@testing-library/react-hooks';
import { useUsers, UsersProvider, useUser } from './users-provider';
import { users } from '../../services/ad-api';

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
  const fetch = jest
    .spyOn(users, 'getAllUsers')
    .mockImplementation(async () => {
      return [
        {
          id: '123',
          customerId: '456',
          email: 'peter@example.com',
          firstName: 'Peter',
          lastName: 'Pane',
          createdAt: '2018-04-11T12:15:30.000+00:00',
          updatedAt: null,
          deletedAt: null
        }
      ];
    });

  await act(async () => {
    const { result } = renderHook(() => useUsers(), {
      wrapper: UsersProvider
    });

    // expect(fetch).toHaveBeenCalledTimes(1);

    expect(result.current.users).toEqual([
      {
        id: '123',
        customerId: '456',
        email: 'peter@example.com',
        firstName: 'Peter',
        lastName: 'Pane',
        createdAt: '2018-04-11T12:15:30.000+00:00',
        updatedAt: null,
        deletedAt: null
      }
    ]);
  });
});
