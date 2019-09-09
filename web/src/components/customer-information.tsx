import React from 'react';
import { SubTitle } from '../components/generics/text';
import Input from './generics/input';
import { isStringEmpty, isValidEmail } from '../lib/validators';
import { useInputValidation } from 'use-input-validation';
import { InvButton, PrimaryButton } from './generics/button';
import Pencil from 'mdi-react/PencilIcon';
import styles from './customer-information.module.scss';

const stringNotEmpty = (val: string) => !isStringEmpty(val);

interface CustomerInformationProps {
  onSave: (
    customerID: string,
    surname: string,
    lastname: string,
    email: string
  ) => void;
}

const CustomerInformation: React.FC<CustomerInformationProps> = ({
  onSave
}) => {
  const customerId = useInputValidation<string, string>(
    '',
    'Keine valide Kundennummer',
    stringNotEmpty
  );

  const surname = useInputValidation<string, string>(
    '',
    'Kein valider Vorname',
    stringNotEmpty
  );

  const lastname = useInputValidation<string, string>(
    '',
    'Kein valider Nachname',
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
    const surnameValid = surname.validate();
    const lastnameValid = lastname.validate();
    const emailValid = email.validate();

    if (!(customerIdValid && surnameValid && lastnameValid && emailValid))
      return;

    onSave(customerId.value, surname.value, lastname.value, email.value);
  }

  return (
    <div>
      <div className={styles.header}>
        <SubTitle> Kunden Informationen</SubTitle>
        <InvButton onClick={() => {}}>
          <Pencil />
        </InvButton>
      </div>

      <form onSubmit={handleSubmit} className={styles.inputs}>
        <Input
          id="customerid"
          label="Kundennummer"
          type="text"
          value={customerId.value}
          onChange={value => customerId.setValue(value)}
          onBlur={customerId.validate}
          error={customerId.error}
        />

        <Input
          id="surname"
          label="Vorname"
          type="text"
          value={surname.value}
          onChange={value => surname.setValue(value)}
          onBlur={surname.validate}
          error={surname.error}
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
      </form>
      <div className={styles.button}>
        <PrimaryButton onClick={() => {}}>Speichern</PrimaryButton>
      </div>
    </div>
  );
};

export default CustomerInformation;
