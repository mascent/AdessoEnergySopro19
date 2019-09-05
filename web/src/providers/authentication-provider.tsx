import React, {
  useState,
  useEffect,
  useLayoutEffect,
  useCallback
} from 'react';

interface AuthenticationContext {
  token: string | null;
  login: (username: string, password: string) => void;
  logout: () => void;
}

const AuthenticationContext = React.createContext<
  AuthenticationContext | undefined
>(undefined);

function useToken() {
  const [token, setToken] = useState<string | null | undefined>(undefined);

  useEffect(() => {
    const token = localStorage.getItem('access_token');

    // TODO: Make sure that the token is not expired
    if (token !== null) setToken(token);
  }, []);

  return token;
}

const AuthenticationProvider: React.FC = ({ children }) => {
  const token = useToken();

  useLayoutEffect(() => {
    if (typeof token !== 'undefined' && token !== null) {
      // TODO: Set config token
    }
  }, [token]);

  const login = useCallback((username: string, password: string) => {
    // TODO: Figure out what we have to do
  }, []);

  const logout = useCallback(() => {}, []);

  return typeof token !== 'undefined' ? (
    <AuthenticationContext.Provider value={{ token, login, logout }}>
      {children}
    </AuthenticationContext.Provider>
  ) : null;
};

function useAuth(): {
  token: string | null;
  login: (username: string, password: string) => void;
  logout: () => void;
} {
  const context = React.useContext(AuthenticationContext);

  if (typeof context === 'undefined')
    throw new Error('useAuth must be used inside a AuthenticationProvider.');

  return context;
}
