import React from 'react';
import Illustrations from './generics/illustrations';
import { PrimaryButton } from './generics/button';
import ContainerCard from './generics/container-card';
import Logo from './generics/logo';
import Input from './generics/input';
import styles from './login-form.module.scss';
import { useInputValidation } from 'use-input-validation';
import { isStringEmpty } from '../lib/validators';

const stringNotEmpty = (val: string) => !isStringEmpty(val);

interface LoginProps {
  onLogin: (customerId: string, password: string) => void;
}

const LoginForm: React.FC<LoginProps> = ({ onLogin }) => {
  const customerId = useInputValidation<string, string>(
    '',
    'Keine valide Kundennummer',
    stringNotEmpty
  );

  const password = useInputValidation<string, string>(
    '',
    'Kein valides Passwort',
    stringNotEmpty
  );

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const customerIdValid = customerId.validate();
    const passwordValid = password.validate();

    if (!(customerIdValid && passwordValid)) return;

    onLogin(customerId.value, password.value);
  }

  return (
    <div>
      <ContainerCard className={styles.loginContainer}>
        <Illustrations className={styles.illustrationsForm} type="Auth" />
        <form className={styles.loginForm} onSubmit={handleSubmit}>
          <Logo type="text-horizontal-stacked" />
          <Input
            id="customerId"
            type="text"
            label="Benutzername"
            value={customerId.value}
            onChange={value => customerId.setValue(value)}
            onBlur={customerId.validate}
            error={customerId.error}
          />
          <Input
            id="password"
            type="password"
            label="Passwort"
            value={password.value}
            onChange={value => password.setValue(value)}
            onBlur={password.validate}
            error={password.error}
          />
          <PrimaryButton className={styles.buttonForm} type="submit">
            Login
          </PrimaryButton>
        </form>
      </ContainerCard>
    </div>
  );
};

export default LoginForm;
