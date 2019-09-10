import React from 'react';
import { storiesOf } from '@storybook/react';
import CustomerInformation from './customer-information';
import { action } from '@storybook/addon-actions';

storiesOf('Form | Kunden Informationen', module)
  .addParameters({ jest: ['button'] })
  .add('Default', () => (
    <CustomerInformation onSave={action('Edit customer')} />
  ));
