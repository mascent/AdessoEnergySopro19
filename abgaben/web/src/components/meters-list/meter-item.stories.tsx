import React from 'react';
import { storiesOf } from '@storybook/react';
import {
  withKnobs,
  text,
  OptionsKnobOptions,
  optionsKnob
} from '@storybook/addon-knobs';
import MeterItem from './meter-item';
import { MeterType } from '../../typings/provider-data-interfaces';
import { WithRouter } from '../../utils/with-router';

const types: {
  [key: string]: MeterType;
} = {
  Gas: 'Gas',
  Water: 'Water',
  Electricity: 'Electricity'
};
const defaultType: MeterType = 'Gas';
const optionsObj: OptionsKnobOptions = {
  display: 'inline-radio'
};

storiesOf('Dashboard Content | MeterItem', module)
  .addDecorator(withKnobs)
  .add('default', () => (
    <WithRouter>
      <MeterItem
        id="123"
        type={optionsKnob<MeterType>('Type', types, defaultType, optionsObj)}
        name={text('Name', 'Zählername')}
        meterNumber={text('Zählernummer', '23.2402.44')}
        date={text('Datum', '24/10/2994')}
      />
    </WithRouter>
  ));
