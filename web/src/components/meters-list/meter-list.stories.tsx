import React from 'react';
import { storiesOf } from '@storybook/react';
import MeterList from './meter-list';
import { action } from '@storybook/addon-actions';
import { Meter } from '../../typings/provider-data-interfaces';
import { buildMeter } from '../../utils/fake-builder';
import StoryRouter from 'storybook-react-router';

const meters: Meter[] = [
  buildMeter(),
  buildMeter(),
  buildMeter(),
  buildMeter(),
  buildMeter(),
  buildMeter(),
  buildMeter(),
  buildMeter(),
  buildMeter(),
  buildMeter()
];

storiesOf('Dashboard Content | MeterList', module)
  .addDecorator(StoryRouter())
  .add('empty', () => (
    <MeterList onAddMeterClick={action('Adding meter')} meters={[]} />
  ))
  .add('filled', () => (
    <div style={{ height: '70vh' }}>
      <MeterList onAddMeterClick={action('Adding meter')} meters={meters} />
    </div>
  ));
