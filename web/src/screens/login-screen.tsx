import React from 'react';
import LoginForm from '../components/login-form';
import styles from './login-screen.module.scss';

const LoginScreen: React.FC = () => {
  return (
    <main className={styles.main}>
      <LoginForm onLogin={() => console.log('Logging in..')} />
    </main>
  );
};

export default LoginScreen;
