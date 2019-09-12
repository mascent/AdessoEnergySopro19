import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import NewMeter from './new-meter';

test('New Meter sends Informationen', () => {
  const handler = jest.fn();
  const { getByLabelText, getByText } = render(
    <NewMeter onCancel={() => {}} onCreate={handler} />
  );

  const metertype = getByLabelText('Zählertyp');
  const name = getByLabelText('Name');
  const meterNumber = getByLabelText('Zählernummer');
  const initialValue = getByLabelText('Initialer Wert');

  const button = getByText('Erstellen');

  fireEvent.change(name, { target: { value: 'Wasserzähler' } });
  fireEvent.change(metertype, { target: { value: 'Water' } });
  fireEvent.change(meterNumber, { target: { value: '42' } });
  fireEvent.change(initialValue, { target: { value: '0' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(1);
});

test('New Meter sends right Informationen', () => {
  const handler = jest.fn();
  const { getByLabelText, getByText } = render(
    <NewMeter onCancel={() => {}} onCreate={handler} />
  );

  const metertype = getByLabelText('Zählertyp');
  const name = getByLabelText('Name');
  const meterNumber = getByLabelText('Zählernummer');
  const initialValue = getByLabelText('Initialer Wert');

  const button = getByText('Erstellen');

  fireEvent.change(name, { target: { value: 'Wasserzähler' } });
  fireEvent.change(metertype, { target: { value: 'Water' } });
  fireEvent.change(meterNumber, { target: { value: '42' } });
  fireEvent.change(initialValue, { target: { value: '0' } });

  fireEvent.click(button);

  expect(handler).toBeCalledWith('Water', 'Wasserzähler', '42', '0');
});

test('New Meter dont send Information when Information are missing', () => {
  const handler = jest.fn();
  const { getByLabelText, getByText } = render(
    <NewMeter onCancel={() => {}} onCreate={handler} />
  );

  const metertype = getByLabelText('Zählertyp');
  const meterNumber = getByLabelText('Zählernummer');
  const initialValue = getByLabelText('Initialer Wert');

  const button = getByText('Erstellen');

  fireEvent.change(metertype, { target: { value: 'Water' } });
  fireEvent.change(meterNumber, { target: { value: '42' } });
  fireEvent.change(initialValue, { target: { value: '0' } });

  fireEvent.click(button);

  expect(handler).toBeCalledTimes(0);
});
