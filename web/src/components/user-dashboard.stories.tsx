import React from 'react';
import { storiesOf } from '@storybook/react';
import UserDashboard from './user-dashboard';
import { MetersProvider } from '../providers/meters-provider';
import { withKnobs, boolean } from '@storybook/addon-knobs';
import { Meter } from '../typings/provider-data-interfaces';
import { buildMeter } from '../utils/fake-builder';
import { WithRouter } from '../utils/with-router';

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
  buildMeter(),
  buildMeter()
];

storiesOf('Dashboards | UserDashboard', module)
  .addDecorator(withKnobs)
  .add('default', () => (
    <WithRouter>
      <MetersProvider
        override={{
          isLoading: boolean('Loading', false),
          meters: meters,
          addMeter: async () => {},
          fetchMeters: async () => {},
          updateMeter: async () => {}
        }}
      >
        <UserDashboard />
      </MetersProvider>
    </WithRouter>
  ));
