import React from 'react';
import { storiesOf } from '@storybook/react';
import ContainerCard from './container-card';

storiesOf('Generics | Container Cards', module).add('default', () => (
  <ContainerCard>
    <p>Content</p>
  </ContainerCard>
));
