import React from 'react';
import Illustrations from './generics/illustrations';
import { PrimaryButton } from './generics/button';
import ContainerCard from './generics/container-card';
import Logo from './generics/logo';
import Input from './generics/input';
import styles from './login-form.module.scss';

const LoginForm: React.FC = () => {
  return (
    <div>
      <ContainerCard className={styles.loginContainer}>
        <Illustrations type="Auth" />
        <form className={styles.loginForm}>
          <Logo type="text-horizontal-stacked" />
          <Input id="Kundennummer" type="text" label="Benutzername"></Input>
          <Input id="Password" type="text" label="Password"></Input>
          <PrimaryButton onClick={() => {}}> LogIn </PrimaryButton>
        </form>
      </ContainerCard>
    </div>
  );
};

export default LoginForm;
