import React from 'react';
import { storiesOf } from '@storybook/react';
import MeterIcon from './meter-icon';

storiesOf('Generics | MeterIcon', module)
  .add('gas', () => <MeterIcon type="gas" />)
  .add('water', () => <MeterIcon type="water" />)
  .add('electricity', () => <MeterIcon type="electricity" />);
