import React from 'react';
import { useAuth } from './providers/authentication-provider';
import UnAuthenticatedApp from './unauthenticated-app';
import AdminApp from './admin-app';
import UserApp from './user-app';
import { SnackBarProvider } from './providers/snackbar-provider';

const App: React.FC = () => {
  const { isLoggedIn, isAdmin } = useAuth();

  // Make sure that the user can only see areas of the app he is allowed to see
  return (
    <SnackBarProvider>
      {!isLoggedIn ? (
        <UnAuthenticatedApp />
      ) : isAdmin ? (
        <AdminApp />
      ) : (
        <UserApp />
      )}
    </SnackBarProvider>
  );
};

export default App;
