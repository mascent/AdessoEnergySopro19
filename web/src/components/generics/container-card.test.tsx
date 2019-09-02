import React from 'react';
import { render } from '@testing-library/react';
import ContainerCard from './container-card';

test('card renders provided children', () => {
  const { getByText } = render(
    <ContainerCard>
      <span>Test</span>
    </ContainerCard>
  );

  getByText('Test');
});
