import React from 'react';
import { storiesOf } from '@storybook/react';
import MeterList from './meter-list';
import { action } from '@storybook/addon-actions';
import { Meter } from '../../typings/provider-data-interfaces';
import { buildMeter, buildList } from '../../utils/fake-builder';
import StoryRouter from 'storybook-react-router';

const meters: Meter[] = buildList(buildMeter, 5, 100);

storiesOf('Dashboard Content | MeterList', module)
  .addDecorator(StoryRouter())
  .add('empty', () => <MeterList meters={[]} />)
  .add('filled', () => (
    <div style={{ height: '70vh' }}>
      <MeterList meters={meters} />
    </div>
  ));
