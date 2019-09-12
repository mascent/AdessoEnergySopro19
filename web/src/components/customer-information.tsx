import React, { useState } from 'react';
import { SubTitle, Span } from '../components/generics/text';
import Input from './generics/input';
import { isStringEmpty, isValidEmail } from '../lib/validators';
import { useInputValidation } from 'use-input-validation';
import { InvButton, PrimaryButton, SecondaryButton } from './generics/button';
import Pencil from 'mdi-react/PencilIcon';
import styles from './customer-information.module.scss';
import { RouteComponentProps } from '@reach/router';

const stringNotEmpty = (val: string) => !isStringEmpty(val);

interface CustomerInformationProps extends RouteComponentProps {
  userInfo: {
    customerId: string;
    firstName: string;
    lastName: string;
    email: string;
  };
  onSave: (firstName: string, lastname: string, email: string) => void;
}

const CustomerInformation: React.FC<CustomerInformationProps> = ({
  onSave,
  userInfo
}) => {
  const firstName = useInputValidation<string, string>(
    userInfo.firstName,
    'Vorname darf nicht leer sein',
    stringNotEmpty
  );

  const lastname = useInputValidation<string, string>(
    userInfo.lastName,
    'Nachname darf nicht leer sein',
    stringNotEmpty
  );

  const email = useInputValidation<string, string>(
    userInfo.email,
    'Keine valide Email',
    isValidEmail
  );

  const [isEditing, setEdit] = useState(false);

  function toggleEdit() {
    // Reset inputs before we start/stop editing
    firstName.reset();
    lastname.reset();
    email.reset();
    setEdit(e => !e);
  }

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const firstnameValid = firstName.validate();
    const lastnameValid = lastname.validate();
    const emailValid = email.validate();

    if (!(firstnameValid && lastnameValid && emailValid)) return;

    onSave(firstName.value, lastname.value, email.value);
    setEdit(false);
  }

  return (
    <div>
      <div className={styles.header}>
        <SubTitle> Kunden Informationen</SubTitle>
        <InvButton title="Informationen bearbeiten" onClick={toggleEdit}>
          <Pencil />
        </InvButton>
      </div>

      {!isEditing && (
        <section>
          <div className={styles.textInfo}>
            <Span className={styles.bold}>Kundennummer</Span>
            <Span>{userInfo.customerId}</Span>
          </div>
          <div className={styles.textInfo}>
            <Span className={styles.bold}>Vorname</Span>
            <Span>{userInfo.firstName}</Span>
          </div>
          <div className={styles.textInfo}>
            <Span className={styles.bold}>Nachname</Span>
            <Span>{userInfo.lastName}</Span>
          </div>
          <div className={styles.textInfo}>
            <Span className={styles.bold}>Email</Span>
            <Span>{userInfo.email}</Span>
          </div>
        </section>
      )}

      {isEditing && (
        <form onSubmit={handleSubmit} className={styles.inputs}>
          <Input
            id="surname"
            label="Vorname"
            type="text"
            value={firstName.value}
            onChange={value => firstName.setValue(value)}
            onBlur={firstName.validate}
            error={firstName.error}
          />
          <Input
            id="lastname"
            label="Nachname"
            type="text"
            value={lastname.value}
            onChange={value => lastname.setValue(value)}
            onBlur={lastname.validate}
            error={lastname.error}
          />
          <Input
            id="email"
            label="Email"
            type="text"
            value={email.value}
            onChange={value => email.setValue(value)}
            onBlur={email.validate}
            error={email.error}
          />
          <div className={styles.button}>
            <SecondaryButton
              className={styles.mRight}
              type="reset"
              onClick={toggleEdit}
            >
              Abbrechen
            </SecondaryButton>
            <PrimaryButton type="submit">Speichern</PrimaryButton>
          </div>
        </form>
      )}
    </div>
  );
};

export default CustomerInformation;
