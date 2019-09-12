import React from 'react';
import { SubTitle } from '../generics/text';
import Input from '../generics/input';
import styles from './new-user-form.module.scss';
import { PrimaryButton, SecondaryButton } from '../generics/button';
import { useInputValidation } from 'use-input-validation';
import { isStringEmpty, isValidEmail, isNumber } from '../../lib/validators';
import { RouteComponentProps } from '@reach/router';

interface NewUserFormProps extends RouteComponentProps {
  onCreate: (
    customerId: string,
    firstName: string,
    lastName: string,
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
    isNumber
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

  const firstname = useInputValidation<string, string>(
    '',
    'Vorname darf nicht leer sein',
    stringNotEmpty
  );

  const lastName = useInputValidation<string, string>(
    '',
    'Nachname darf nicht leer sein',
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
    const firstNameValid = firstname.validate();
    const lastNameValid = lastName.validate();
    const emailValid = email.validate();
    const passwordValidatorValid = passwordValidator.validate();

    if (
      !(
        customerIdValid &&
        passwordValid &&
        firstNameValid &&
        lastNameValid &&
        emailValid &&
        passwordValidatorValid
      )
    )
      return;

    onCreate(
      customerId.value,
      firstname.value,
      lastName.value,
      email.value,
      password.value
    );
  }

  return (
    <form className={styles.container} onSubmit={handleSubmit}>
      <SubTitle> Neuen Kunden erfassen</SubTitle>
      <Input
        id="kundennummer"
        type="number"
        label="Kundennummer"
        value={customerId.value}
        onChange={value => customerId.setValue(value)}
        onBlur={customerId.validate}
        error={customerId.error}
      />
      <Input
        id="email"
        type="text"
        label="Email"
        onChange={value => email.setValue(value)}
        onBlur={email.validate}
        error={email.error}
      />
      <div className={styles.nameEmail}>
        <Input
          id="firstname"
          type="text"
          label="Vorname"
          value={firstname.value}
          onChange={value => firstname.setValue(value)}
          onBlur={firstname.validate}
          error={firstname.error}
          className={styles.input}
        />
        <Input
          id="lastname"
          type="text"
          label="NAchname"
          value={lastName.value}
          onChange={value => lastName.setValue(value)}
          onBlur={lastName.validate}
          error={lastName.error}
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
        <SecondaryButton onClick={onCancel} type="reset">
          Abbrechen
        </SecondaryButton>
        <PrimaryButton type="submit">Erstellen</PrimaryButton>
      </div>
    </form>
  );
};

export default NewUserForm;
