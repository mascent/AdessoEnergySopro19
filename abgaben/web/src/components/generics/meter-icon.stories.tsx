import React from 'react';
import { storiesOf } from '@storybook/react';
import MeterIcon from './meter-icon';

storiesOf('Generics | MeterIcon', module)
  .add('gas', () => <MeterIcon type="Gas" />)
  .add('water', () => <MeterIcon type="Water" />)
  .add('electricity', () => <MeterIcon type="Electricity" />);
