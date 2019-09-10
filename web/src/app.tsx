import React from 'react';
import { useAuth } from './providers/authentication-provider';
import UnAuthenticatedApp from './unauthenticated-app';
import AdminApp from './admin-app';
import UserApp from './user-app';

const App: React.FC = () => {
  const { isLoggedIn, isAdmin } = useAuth();

  // Make sure that the user can only see areas of the app he is allowed to see
  if (!isLoggedIn) {
    return <UnAuthenticatedApp />;
  } else if (isAdmin) {
    return <AdminApp />;
  } else {
    return <UserApp />;
  }
};

export default App;
