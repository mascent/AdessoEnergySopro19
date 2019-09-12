import React from 'react';
import { SubTitle } from '../generics/text';
import styles from './new-meter.module.scss';
import { useInputValidation } from 'use-input-validation';
import { isStringEmpty, isMeterTypeValid } from '../../lib/validators';
import Input from '../generics/input';
import { PrimaryButton, SecondaryButton } from '../generics/button';
import { RouteComponentProps } from '@reach/router';
import { MeterType } from '../../typings/provider-data-interfaces';

const stringNotEmpty = (val: string) => !isStringEmpty(val);

interface NewMeterProps extends RouteComponentProps {
  onCancel: () => void;
  onCreate: (
    meterType: MeterType,
    name: string,
    meterNumber: string,
    initialValue: string
  ) => void;
}

const NewMeter: React.FC<NewMeterProps> = ({ onCreate, onCancel }) => {
  const meterType = useInputValidation<string, string>(
    '',
    'Keine valider Zählertyp. Wähle aus zwischen: Gas, Water, Electricity',
    isMeterTypeValid
  );

  const name = useInputValidation<string, string>(
    '',
    'Name darf nicht leer sein',
    stringNotEmpty
  );

  const meterNumber = useInputValidation<string, string>(
    '',
    'Keine valide Zählernummer',
    stringNotEmpty
  );

  const initialValue = useInputValidation<string, string>(
    '',
    'Startwert darf nicht leer sein',
    stringNotEmpty
  );

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const meterTypeValid = meterType.validate();
    const nameValid = name.validate();
    const meterNumberValid = meterNumber.validate();
    const initialValueValid = initialValue.validate();

    if (!(meterTypeValid && meterNumberValid && nameValid && initialValueValid))
      return;

    // For typescript. Make sure that the string really matches the MeterType
    if (
      meterType.value !== 'Gas' &&
      meterType.value !== 'Electricity' &&
      meterType.value !== 'Water'
    )
      return;

    onCreate(
      meterType.value,
      name.value,
      meterNumber.value,
      initialValue.value
    );
  }

  return (
    <div className={styles.container}>
      <SubTitle className={styles.header}>Neuen Zähler erfassen</SubTitle>
      <form onSubmit={handleSubmit}>
        <Input
          id="metertype"
          type="text"
          label="Zählertyp"
          value={meterType.value}
          onChange={value => meterType.setValue(value)}
          onBlur={meterType.validate}
          error={meterType.error}
        />

        <Input
          id="name"
          type="text"
          label="Name"
          value={name.value}
          onChange={value => name.setValue(value)}
          onBlur={name.validate}
          error={name.error}
        />

        <Input
          id="meterNumber"
          type="text"
          label="Zählernummer"
          value={meterNumber.value}
          onChange={value => meterNumber.setValue(value)}
          onBlur={meterNumber.validate}
          error={meterNumber.error}
        />

        <Input
          id="initialValue"
          type="number"
          label="Initialer Wert"
          value={initialValue.value}
          onChange={value => initialValue.setValue(value)}
          onBlur={initialValue.validate}
          error={initialValue.error}
        />

        <div className={styles.buttons}>
          <SecondaryButton
            onClick={onCancel}
            className={styles.cancelButton}
            type="reset"
          >
            Abbrechen
          </SecondaryButton>
          <PrimaryButton type="submit">Erstellen</PrimaryButton>
        </div>
      </form>
    </div>
  );
};

export default NewMeter;
