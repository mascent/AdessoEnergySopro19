import React from 'react';
import { SubTitle } from '../components/generics/text';
import Input from './generics/input';
import styles from './new-user-form.module.scss';
import { PrimaryButton, SecondaryButton } from './generics/button';
import { useInputValidation } from 'use-input-validation';
import { isStringEmpty, isValidEmail } from '../lib/validators';

interface NewUserFormProps {
  onCreate: (
    customerId: string,
    name: string,
    email: string,
    password: string
  ) => void;
  onCancel: () => void;
}

const stringNotEmpty = (val: string) => !isStringEmpty(val);

const confirmedPasswordValidator = (password: string) => (value: string) => {
  const trimmed = value.trim();
  return trimmed !== '' && trimmed === password;
};

const NewUserForm: React.FC<NewUserFormProps> = ({ onCreate, onCancel }) => {
  const customerId = useInputValidation<string, string>(
    '',
    'Keine valide Kundennummer',
    stringNotEmpty
  );

  const password = useInputValidation<string, string>(
    '',
    'Passwort darf nicht leer sein',
    stringNotEmpty
  );

  const passwordValidator = useInputValidation<string, string>(
    '',
    'Bestätigtes Passwort ist nicht gleich',
    confirmedPasswordValidator(password.value)
  );

  const name = useInputValidation<string, string>(
    '',
    'Name darf nicht leer sein',
    stringNotEmpty
  );

  const email = useInputValidation<string, string>(
    '',
    'Keine valide Email',
    isValidEmail
  );

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const customerIdValid = customerId.validate();
    const passwordValid = password.validate();
    const nameValid = name.validate();
    const emailValid = email.validate();
    const passwordValidatorValid = passwordValidator.validate();

    if (
      !(
        customerIdValid &&
        passwordValid &&
        nameValid &&
        emailValid &&
        passwordValidatorValid
      )
    )
      return;

    onCreate(customerId.value, name.value, password.value, email.value);
  }

  return (
    <form className={styles.container} onSubmit={handleSubmit}>
      <SubTitle> Neuen Kunden erfassen</SubTitle>
      <Input
        id="kundennummer"
        type="text"
        label="Kundennummer"
        value={customerId.value}
        onChange={value => customerId.setValue(value)}
        onBlur={customerId.validate}
        error={customerId.error}
      />
      <div className={styles.nameEmail}>
        <Input
          id="name"
          type="text"
          label="Name"
          value={name.value}
          onChange={value => name.setValue(value)}
          onBlur={name.validate}
          error={name.error}
          className={styles.input}
        />

        <Input
          id="email"
          type="text"
          label="Email"
          onChange={value => email.setValue(value)}
          onBlur={email.validate}
          error={email.error}
          className={styles.input}
        />
      </div>
      <div className={styles.password}>
        <Input
          id="password"
          type="password"
          label="Passwort"
          onChange={value => password.setValue(value)}
          onBlur={password.validate}
          error={password.error}
          className={styles.input}
        />

        <Input
          id="validatePassword"
          type="password"
          label="Passwort bestätigen"
          onChange={value => passwordValidator.setValue(value)}
          onBlur={passwordValidator.validate}
          error={passwordValidator.error}
          className={styles.input}
        />
      </div>
      <div className={styles.button}>
        <SecondaryButton onClick={() => {}}>Abbrechen</SecondaryButton>
        <PrimaryButton onClick={() => {}}>Erstellen</PrimaryButton>
      </div>
    </form>
  );
};

export default NewUserForm;
