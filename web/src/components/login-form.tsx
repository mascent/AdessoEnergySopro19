import React from 'react';
import Illustrations from './generics/illustrations';
import { PrimaryButton } from './generics/button';
import ContainerCard from './generics/container-card';
import Logo from './generics/logo';
import Input from './generics/input';
import styles from './login-form.module.scss';
import { useInputValidation } from 'use-input-validation';
import { isStringEmpty } from '../lib/validators';

const LoginForm: React.FC = () => {
  const kundennummer = useInputValidation<string, string>(
    '',
    'Keine valide Kundennummer',
    isStringEmpty
  );

  const password = useInputValidation<string, string>(
    '',
    'Kein valides Passwort',
    isStringEmpty
  );

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const kundennummerValid = kundennummer.validate();
    const passwordValid = password.validate();

    if (!(kundennummerValid && passwordValid)) return;
  }

  return (
    <div>
      <ContainerCard className={styles.loginContainer}>
        <Illustrations className={styles.illustrationsForm} type="Auth" />
        <form className={styles.loginForm} onSubmit={handleSubmit}>
          <Logo type="text-horizontal-stacked" />
          <Input
            id="Kundennummer"
            type="text"
            label="Benutzername"
            value={kundennummer.value}
            onChange={value => kundennummer.setValue(value)}
            onBlur={kundennummer.validate}
            error={kundennummer.error}
          />
          <Input
            id="Password"
            type="password"
            label="Passwort"
            value={password.value}
            onChange={value => password.setValue(value)}
            onBlur={password.validate}
            error={password.error}
          />
          <PrimaryButton className={styles.buttonForm} onClick={() => {}}>
            {' '}
            Login{' '}
          </PrimaryButton>
        </form>
      </ContainerCard>
    </div>
  );
};

export default LoginForm;
