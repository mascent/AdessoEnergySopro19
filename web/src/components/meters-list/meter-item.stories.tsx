import React from 'react';
import { storiesOf } from '@storybook/react';
import {
  withKnobs,
  text,
  number,
  OptionsKnobOptions,
  optionsKnob
} from '@storybook/addon-knobs';
import StoryRouter from 'storybook-react-router';
import MeterItem from './meter-item';
import { MeterType } from '../../typings/provider-data-interfaces';

const types: {
  [key: string]: MeterType;
} = {
  Gas: 'gas',
  Water: 'water',
  Electricity: 'electricity'
};
const defaultType: MeterType = 'gas';
const optionsObj: OptionsKnobOptions = {
  display: 'inline-radio'
};

storiesOf('Dashboard Content | MeterItem', module)
  .addDecorator(withKnobs)
  .addDecorator(StoryRouter())
  .add('default', () => (
    <MeterItem
      id="123"
      type={optionsKnob<MeterType>('Type', types, defaultType, optionsObj)}
      name={text('Name', 'Zählername')}
      meterNumber={text('Zählernummer', '23.2402.44')}
      date={text('Datum', '24/10/2994')}
      trend={number('Trend', 4)}
    />
  ));
