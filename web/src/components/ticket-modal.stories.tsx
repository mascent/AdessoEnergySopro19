import React from 'react';
import { storiesOf } from '@storybook/react';
import TicketModal from './ticket-modal';

storiesOf('Forms | TicketModal', module).add('Default', () => (
  <TicketModal onSend={() => {}} isOpen={true} closeModal={() => {}} />
));
