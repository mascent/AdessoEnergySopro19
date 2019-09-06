import React from 'react';
import LoginForm from '../components/login-form';
import styles from './login-screen.module.scss';
import { useAuth } from '../providers/authentication-provider';

const LoginScreen: React.FC = () => {
  const { login } = useAuth();

  return (
    <main className={styles.main}>
      <LoginForm onLogin={login} />
    </main>
  );
};

export default LoginScreen;
