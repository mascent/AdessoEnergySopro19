import React from 'react';
import { storiesOf } from '@storybook/react';
import UserDashboard from './user-dashboard';
import StoryRouter from 'storybook-react-router';
import { MetersProvider } from '../providers/meters-provider';
import { withKnobs, boolean } from '@storybook/addon-knobs';
import { Meter } from '../typings/provider-data-interfaces';
import { buildMeter } from '../utils/fake-builder';

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
  .addDecorator(StoryRouter(undefined, { initialEntries: ['/counters'] }))
  .addDecorator(withKnobs)
  .add('default', () => (
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
  ));
