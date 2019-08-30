import React from 'react';
import { storiesOf } from '@storybook/react';
import ContainerCard from './container-card';

storiesOf('Generics | Container Card', module)
  .addParameters({ jest: ['container-card'] })
  .add('default', () => (
    <ContainerCard>
      <p>Content</p>
    </ContainerCard>
  ));
