import React from 'react';
import { storiesOf } from '@storybook/react';
import Logo from './logo';

storiesOf('Generics | Logo', module)
  .add('with background', () => <Logo type="with-bg" />)
  .add('no text', () => <Logo type="no-text" />)
  .add('text horizontal', () => <Logo type="text-horizontal" />)
  .add('text vertical', () => <Logo type="text-vertical" />)
  .add('text horizontal stacked', () => (
    <Logo type="test-horizontal-stacked" />
  ));
