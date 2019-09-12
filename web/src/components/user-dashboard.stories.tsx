import React from 'react';
import { storiesOf } from '@storybook/react';
import UserDashboard from './user-dashboard';
import { MetersProvider } from '../providers/meters-provider';
import { withKnobs, boolean } from '@storybook/addon-knobs';
import { Meter } from '../typings/provider-data-interfaces';
import { buildMeter, buildList } from '../utils/fake-builder';
import { WithRouter } from '../utils/with-router';

const meters: Meter[] = buildList(buildMeter, 5, 100);

storiesOf('Dashboards | UserDashboard', module)
  .addDecorator(withKnobs)
  .add('default', () => (
    <WithRouter>
      <MetersProvider
        override={{
          isLoading: boolean('Loading', false),
          meters: meters,
          addMeter: async () => true,
          fetchMeters: async () => true,
          updateMeter: async () => true
        }}
      >
        <UserDashboard />
      </MetersProvider>
    </WithRouter>
  ));
