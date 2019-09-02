import React, { useState } from 'react';
import Input from './input';
import { storiesOf } from '@storybook/react';
import { withKnobs, text } from '@storybook/addon-knobs';

const Container: React.FC = () => {
  const [value, setValue] = useState('');
  return (
    <Input
      id="testLabel"
      type="text"
      label={text('Label', 'Benutzername')}
      placeholder={text('Placeholder', 'Placeholder')}
      value={value}
      onChange={setValue}
      error={text('Fehlermeldung', '')}
    />
  );
};

storiesOf('Generics | Input', module)
  .addParameters({ jest: ['input'] })
  .addDecorator(withKnobs)
  .add('input', () => <Container />);
