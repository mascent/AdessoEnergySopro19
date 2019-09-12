import React from 'react';
import { storiesOf } from '@storybook/react';
import MeterInformation from './meter-information';
import { WithRouter } from '../../utils/with-router';
import { MetersProvider } from '../../providers/meters-provider';
import { withKnobs, boolean } from '@storybook/addon-knobs';
import { buildList, buildMeter, buildReading } from '../../utils/fake-builder';
import { ReadingsProvider } from '../../providers/readings-provider';
import { AuthenticationProvider } from '../../providers/authentication-provider';
import { SnackBarProvider } from '../../providers/snackbar-provider';

const meters = buildList(buildMeter, 5, 100);
const readings = buildList(buildReading, 5, 50);

storiesOf('Dashboard Content | MeterInformation', module)
  .addDecorator(withKnobs)
  .add('default', () => (
    <WithRouter>
      <SnackBarProvider>
        <AuthenticationProvider>
          <MetersProvider
            override={{
              isLoading: boolean('Loading meters', false),
              meters: meters,
              addMeter: async () => true,
              fetchMeters: async () => true,
              updateMeter: async () => true
            }}
          >
            <ReadingsProvider
              override={{
                isLoading: boolean('Loading readings', false),
                readings: readings,
                addReading: async () => true,
                fetchReadings: async () => true,
                updateReading: async () => true
              }}
            >
              <MeterInformation id={meters.length > 0 ? meters[0].id : ''} />
            </ReadingsProvider>
          </MetersProvider>
        </AuthenticationProvider>
      </SnackBarProvider>
    </WithRouter>
  ));
