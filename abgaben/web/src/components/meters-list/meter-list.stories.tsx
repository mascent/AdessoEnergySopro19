import React from 'react';
import { storiesOf } from '@storybook/react';
import MeterList from './meter-list';
import { Meter } from '../../typings/provider-data-interfaces';
import { buildMeter, buildList } from '../../utils/fake-builder';
import { WithRouter } from '../../utils/with-router';

const meters: Meter[] = buildList(buildMeter, 5, 100);

storiesOf('Dashboard Content | MeterList', module)
  .add('empty', () => (
    <WithRouter>
      <MeterList meters={[]} />
    </WithRouter>
  ))
  .add('filled', () => (
    <WithRouter>
      <div style={{ height: '70vh' }}>
        <MeterList meters={meters} />
      </div>
    </WithRouter>
  ));
