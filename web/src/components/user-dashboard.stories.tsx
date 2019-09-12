import React from 'react';
import { storiesOf } from '@storybook/react';
import UserDashboard from './user-dashboard';
import { MetersProvider } from '../providers/meters-provider';
import { withKnobs, boolean } from '@storybook/addon-knobs';
import { Meter } from '../typings/provider-data-interfaces';
import { buildMeter, buildList, buildReading } from '../utils/fake-builder';
import { WithRouter } from '../utils/with-router';
import { AuthenticationProvider } from '../providers/authentication-provider';
import { ReadingsProvider } from '../providers/readings-provider';

const meters: Meter[] = buildList(buildMeter, 5, 100);
const readings: Reading[] = buildList(buildReading, 5, 100);

storiesOf('Dashboards | UserDashboard', module)
  .addDecorator(withKnobs)
  .add('default', () => (
    <WithRouter>
      <AuthenticationProvider>
        <MetersProvider
          override={{
            isLoading: boolean('Loading', false),
            meters: meters,
            addMeter: async () => true,
            fetchMeters: async () => true,
            updateMeter: async () => true
          }}
        >
          <ReadingsProvider
            override={{
              isLoading: boolean('Loading', false),
              readings: readings,
              addReading: async () => true,
              fetchReadings: async () => true,
              updateReading: async () => true
            }}
          >
            <UserDashboard />
          </ReadingsProvider>
        </MetersProvider>
      </AuthenticationProvider>
    </WithRouter>
  ));
