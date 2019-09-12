import React from 'react';
import GraphTest from './graph';
import { storiesOf } from '@storybook/react';
import { buildList, buildReading } from '../../utils/fake-builder';

const building = buildList(buildReading, 10, 100);
const date = building.map(r => r.createdAt.toLocaleDateString());
const data = building.map(r => parseInt(r.value, 10));

storiesOf('Dashboard Content | Graph', module).add('Graph', () => (
  <GraphTest title="Zählerstände" dates={date} data={data} />
));
