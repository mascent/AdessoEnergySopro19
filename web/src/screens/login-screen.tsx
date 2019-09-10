import React from 'react';
import LoginForm from '../components/login-form';
import styles from './login-screen.module.scss';
import { useAuth } from '../providers/authentication-provider';
import useDocumentTitle from '../hooks/use-document-title';
import { useSnackBar } from '../providers/snackbar-provider';

const LoginScreen: React.FC = () => {
  useDocumentTitle('Login');
  const { login } = useAuth();
  const showSnackbar = useSnackBar();

  function handleLogin(username: string, password: string) {
    login(username, password).then(success => {
      if (!success) showSnackbar('warning', 'Username or password wrong');
    });
  }

  return (
    <main className={styles.main}>
      <LoginForm onLogin={handleLogin} />
    </main>
  );
};

export default LoginScreen;
