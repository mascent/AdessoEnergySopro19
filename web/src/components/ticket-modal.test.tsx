import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import TicketModal from './ticket-modal';

test.skip('Ticket sends Issue', () => {
  const handler = jest.fn();

  const { getByLabelText, getByText } = render(
    <TicketModal onSend={handler} isOpen={true} closeModal={() => {}} />
  );
  const name = getByLabelText(/Name/);
  const email = getByLabelText('Email');
  const subject = getByLabelText('Betreff');
  const message = getByLabelText('Nachricht');
  const button = getByText('Senden');

  fireEvent.change(name, { target: { value: 'Name' } });
  fireEvent.change(email, { target: { value: 'name.name@name.de' } });
  fireEvent.change(subject, { target: { value: 'Beispielbetreff' } });
  fireEvent.change(message, { target: { value: 'Test Nachricht' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(1);
  expect(handler).toBeCalledWith([
    'Name',
    'name.name@name.de',
    'Beispielbetreff',
    'Test Nachricht'
  ]);
});
