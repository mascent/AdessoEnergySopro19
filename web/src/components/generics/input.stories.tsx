import React from 'react';
import Input from './input';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import { withKnobs, text } from '@storybook/addon-knobs';

storiesOf('Components | Input', module)
  .addParameters({ jest: ['input'] })
  .addDecorator(withKnobs)
  .add('input', () => (
    <Input
      id="testLabel"
      type="text"
      label={text('Label', 'Benutzername')}
      placeholder={text('Placeholder', 'Placeholder')}
      value="Value"
      onChange={action('Test')}
      error={text('Fehlermeldung', '')}
    />
  ));
