import React, { useState } from 'react';
import Textarea from './textarea';
import { storiesOf } from '@storybook/react';
import { withKnobs, text } from '@storybook/addon-knobs';

const Container: React.FC = () => {
  const [value, setValue] = useState('');
  return (
    <Textarea
      id="testLabel"
      label={text('Label', 'Textarea')}
      placeholder={text('Placeholder', 'Placeholder')}
      value={value}
      onChange={setValue}
      error={text('Fehlermeldung', '')}
    />
  );
};

storiesOf('Generics | Textarea', module)
  .addParameters({ jest: ['textarea'] })
  .addDecorator(withKnobs)
  .add('default', () => <Container />);
