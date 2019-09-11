import React from 'react';
import { storiesOf } from '@storybook/react';
import MeterInformation from './meter-information';
import { WithRouter } from '../../utils/with-router';
import { MetersProvider } from '../../providers/meters-provider';
import { withKnobs, boolean } from '@storybook/addon-knobs';
import { buildList, buildMeter } from '../../utils/fake-builder';

const meters = buildList(buildMeter, 5, 100);

storiesOf('Dashboard Content | MeterInformation', module)
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
        <MeterInformation id={meters.length > 0 ? meters[0].id : ''} />
      </MetersProvider>
    </WithRouter>
  ));
