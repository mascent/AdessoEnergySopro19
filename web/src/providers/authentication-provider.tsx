import React, { useState, useCallback } from 'react';
import { auth, config } from '../services/ad-api';

interface AuthenticationContext {
  isLoggedIn: boolean;
  userId: string;
  isAdmin: boolean;
  login: (username: string, password: string) => Promise<boolean>;
  logout: () => void;
}

const AuthenticationContext = React.createContext<
  AuthenticationContext | undefined
>(undefined);

export const AuthenticationProvider: React.FC = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);
  const [userId, setUserId] = useState<string>('');
  const [isAdmin, setIsAdmin] = useState<boolean>(false);

  const login = useCallback(async (username: string, password: string) => {
    // Set the token with experimental things.
    config.setToken(username, password);
    try {
      const { userId, isAdmin } = await auth.login();

      setIsLoggedIn(true);
      setUserId(userId);
      setIsAdmin(isAdmin);
      return true;
    } catch (e) {
      config.resetToken();
      setIsLoggedIn(false);
      return false;
    }
  }, []);

  const logout = useCallback(() => {
    config.resetToken();
    setIsLoggedIn(false);
    setUserId('');
    setIsAdmin(false);
  }, []);

  return (
    <AuthenticationContext.Provider
      value={{ isLoggedIn, userId, isAdmin, login, logout }}
    >
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

  // Use this if Basic Auth with no sessions is destroying you and you just want
  // to be "logged in"
  // return context;
  return {
    isLoggedIn: true,
    userId: 'kaka',
    isAdmin: false,
    login: async (username: string, password: string) => true,
    logout: () => {}
  };
}
