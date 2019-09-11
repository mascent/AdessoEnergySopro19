import React from 'react';
import { useInputValidation } from 'use-input-validation';
import { isNumber } from '../lib/validators';
import Input from './generics/input';
import { SecondaryButton, PrimaryButton } from './generics/button';
import styles from './new-reading.module.scss';

interface ReadingProps {
  initialValue: string;
  onAdd: (reading: string) => void;
  onClose: () => void;
}

const NewReading: React.FC<ReadingProps> = ({
  initialValue,
  onAdd,
  onClose
}) => {
  const reading = useInputValidation<string, string>(
    initialValue,
    'Zählerstand muss eine positive Zahl sein',
    num => isNumber(num) && parseInt(num, 10) > 0
  );

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const readingValid = reading.validate();

    if (!readingValid) return;

    onAdd(reading.value);
  }

  return (
    <form onSubmit={handleSubmit} className={styles.container}>
      <Input
        id="reading"
        type="number"
        label="Stand"
        value={reading.value}
        onChange={value => reading.setValue(value)}
        onBlur={reading.validate}
        error={reading.error}
      />
      <div className={styles.buttons}>
        <SecondaryButton type="reset" onClick={onClose}>
          Abbrechen
        </SecondaryButton>
        <PrimaryButton type="submit">Hinzufügen</PrimaryButton>
      </div>
    </form>
  );
};

export default NewReading;
