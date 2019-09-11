import React from 'react';
import { storiesOf } from '@storybook/react';
import MeterInformation from './meter-information';
import { WithRouter } from '../../utils/with-router';
import { MetersProvider } from '../../providers/meters-provider';
import { withKnobs, boolean } from '@storybook/addon-knobs';
import { buildList, buildMeter, buildReading } from '../../utils/fake-builder';
import { ReadingsProvider } from '../../providers/readings-provider';

const meters = buildList(buildMeter, 5, 100);
const readings = buildList(buildReading, 5, 50);

storiesOf('Dashboard Content | MeterInformation', module)
  .addDecorator(withKnobs)
  .add('default', () => (
    <WithRouter>
      <MetersProvider
        override={{
          isLoading: boolean('Loading meters', false),
          meters: meters,
          addMeter: async () => {},
          fetchMeters: async () => {},
          updateMeter: async () => {}
        }}
      >
        <ReadingsProvider
          override={{
            isLoading: boolean('Loading readings', false),
            readings: readings,
            addReading: async () => {},
            fetchReadings: async () => {},
            updateReading: async () => {}
          }}
        >
          <MeterInformation id={meters.length > 0 ? meters[0].id : ''} />
        </ReadingsProvider>
      </MetersProvider>
    </WithRouter>
  ));
