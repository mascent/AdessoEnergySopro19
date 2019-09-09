import React from 'react';
import { storiesOf } from '@storybook/react';
import CustomerInformation from './customer-information';

storiesOf('Form | Kunden Informationen', module).add('Default', () => (
  <CustomerInformation />
));
