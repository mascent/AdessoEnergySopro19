import Meters from './meters';
import { storiesOf } from '@storybook/react';
import React from 'react';
import { withKnobs, text } from '@storybook/addon-knobs';

const Meter: React.FC = () => {
  return (
    <Meters
      type="gas"
      name={text('Name', 'Lieblingszähler')}
      meterNumber={text('Zählernummer', '23.2402.44')}
      date={text('Datum', '24/10/2994')}
      compared={text('Vergleich zu vorher', '+3%')}
    />
  );
};

storiesOf('Generics | Meter', module)
  .addDecorator(withKnobs)
  .add('Default', () => <Meter />);
