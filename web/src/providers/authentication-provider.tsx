import React, {
  useState,
  useEffect,
  useLayoutEffect,
  useCallback
} from 'react';
import { auth, config } from '../services/ad-api';

interface AuthenticationContext {
  token: string | null;
  userId: string;
  isAdmin: boolean;
  login: (username: string, password: string) => void;
  logout: () => void;
}

const AuthenticationContext = React.createContext<
  AuthenticationContext | undefined
>(undefined);

function useToken(): [
  string | null | undefined,
  React.Dispatch<React.SetStateAction<string | null | undefined>>
] {
  const [token, setToken] = useState<string | null | undefined>(undefined);

  useEffect(() => {
    const token = localStorage.getItem('access_token');

    // TODO: Make sure that the token is not expired
    if (token !== null) setToken(token);

    setToken(null);
  }, []);

  return [token, setToken];
}

export const AuthenticationProvider: React.FC = ({ children }) => {
  const [token, setToken] = useToken();
  const [userId, setUserId] = useState<string>('');
  const [isAdmin, setIsAdmin] = useState<boolean>(false);

  useLayoutEffect(() => {
    if (typeof token !== 'undefined' && token !== null) {
      config.token = token;
      // TODO: UserId and isAdmin has to be decoded from the jwt token.
      // But: Are we still using JWT when switching to basic auth?
      setUserId('sdjsdk');
      setIsAdmin(false);
    }
  }, [token]);

  const login = useCallback(
    async (username: string, password: string) => {
      const token = await auth.login(username, password);
      setToken(token);
      localStorage.setItem('access_token', token);
    },
    [setToken]
  );

  const logout = useCallback(() => {}, []);

  return typeof token !== 'undefined' ? (
    <AuthenticationContext.Provider
      value={{ token, userId, isAdmin, login, logout }}
    >
      {children}
    </AuthenticationContext.Provider>
  ) : null;
};

export function useAuth(): {
  token: string | null;
  userId: string;
  isAdmin: boolean;
  login: (username: string, password: string) => void;
  logout: () => void;
} {
  const context = React.useContext(AuthenticationContext);

  if (typeof context === 'undefined')
    throw new Error('useAuth must be used inside a AuthenticationProvider.');

  return context;
}
