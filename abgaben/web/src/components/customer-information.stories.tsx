import React from 'react';
import { storiesOf } from '@storybook/react';
import CustomerInformation from './customer-information';
import { action } from '@storybook/addon-actions';
import { buildUser } from '../utils/fake-builder';

const user = buildUser();

storiesOf('Form | Kunden Informationen', module)
  .addParameters({ jest: ['customer-information'] })
  .add('Default', () => (
    <CustomerInformation userInfo={user} onSave={action('Edit customer')} />
  ));
