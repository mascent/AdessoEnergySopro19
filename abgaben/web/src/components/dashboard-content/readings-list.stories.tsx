import React from 'react';
import { storiesOf } from '@storybook/react';
import { buildList, buildReading } from '../../utils/fake-builder';
import ReadingList from './readings-list';
import { withKnobs, boolean } from '@storybook/addon-knobs';
import { action } from '@storybook/addon-actions';

const readings = buildList(buildReading, 5, 50);

storiesOf('Dashboard Content | ReadingList', module)
  .addDecorator(withKnobs)
  .add('default', () => (
    <ReadingList
      unit="kWh"
      readings={readings}
      canEdit={boolean('Can edit', true)}
      onEditClick={action('Editing')}
    />
  ));
