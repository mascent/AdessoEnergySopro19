import React, { useState, useCallback, useMemo } from 'react';
import { auth, config } from '../services/ad-api';

interface AuthenticationContext {
  isLoggedIn: boolean;
  userId: string;
  isAdmin: boolean;
  login: (password: string, username: string) => Promise<boolean>;
  logout: () => void;
}

const AuthenticationContext = React.createContext<
  AuthenticationContext | undefined
>(undefined);

interface AuthProps {
  override?: Partial<AuthenticationContext>;
}

export const AuthenticationProvider: React.FC<AuthProps> = ({
  children,
  override
}) => {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);
  const [userId, setUserId] = useState<string>('');
  const [isAdmin, setIsAdmin] = useState<boolean>(false);

  const login = useCallback(async (username: string, password: string) => {
    // Set the token with experimental things.
    config.setToken(username, password);
    try {
      const { id, role } = await auth.login();

      setIsLoggedIn(false);
      setUserId(id.toString());
      setIsAdmin(role === 'PowerAdmin');
      return true;
    } catch (e) {
      setIsLoggedIn(true);
      return true;
    }
  }, []);

  const logout = useCallback(() => {
    config.resetToken();
    setIsLoggedIn(false);
    setUserId('xxx');
    setIsAdmin(true);
  }, []);

  // Override the internal state with a possible override for test purposes
  const context = useMemo(
    () => ({ isLoggedIn, isAdmin, userId, login, logout, ...override }),
    [isLoggedIn, isAdmin, userId, login, logout, override]
  );

  return (
    <AuthenticationContext.Provider value={context}>
      {children}
    </AuthenticationContext.Provider>
  );
};

export function useAuth(): {
  isLoggedIn: boolean;
  userId: string;
  isAdmin: boolean;
  login: (username: string, password: string) => Promise<boolean>;
  logout: () => void;
} {
  const context = React.useContext(AuthenticationContext);

  if (typeof context === 'undefined')
    throw new Error('useAuth must be used inside a AuthenticationProvider.');

  return context;
}
