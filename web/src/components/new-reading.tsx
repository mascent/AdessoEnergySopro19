import React from 'react';
import { SubTitle, Span } from './generics/text';
import { useInputValidation } from 'use-input-validation';
import { isStringEmpty } from '../lib/validators';
import Input from './generics/input';
import { SecondaryButton, PrimaryButton } from './generics/button';
import styles from './new-reading.module.scss';

const stringNotEmpty = (val: string) => !isStringEmpty(val);

interface ReadingProps {
  onAdd: (reading: string) => void;
}

const NewReading: React.FC<ReadingProps> = ({ onAdd }) => {
  const reading = useInputValidation<string, string>(
    '',
    'Der Zählerstand darf nicht leer sein.',
    stringNotEmpty
  );

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const readingValid = reading.validate();

    if (!readingValid) return;

    onAdd(reading.value);
  }

  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <SubTitle> Einträge </SubTitle>
        <Span>Neuen Einträg erfassen</Span>
      </div>

      <form onSubmit={handleSubmit}>
        <Input
          id="reading"
          type="text"
          label="Stand"
          value={reading.value}
          onChange={value => reading.setValue(value)}
          onBlur={reading.validate}
          error={reading.error}
        />
        <div className={styles.buttons}>
          <SecondaryButton onClick={() => {}}>Abbrechen</SecondaryButton>
          <PrimaryButton onClick={() => {}}>Hinzufügen</PrimaryButton>
        </div>
      </form>
    </div>
  );
};

export default NewReading;
