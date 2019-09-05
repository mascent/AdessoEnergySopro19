import React, { useState, useEffect } from 'react';

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

const AuthenticationProvider: React.FC = () => {
  const token = useToken();

  return null;
};
