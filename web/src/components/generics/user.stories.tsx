import React from 'react';
import { storiesOf } from '@storybook/react';
import User from './user';
import { withKnobs, text } from '@storybook/addon-knobs';

const UserContainer: React.FC = () => {
  return (
    <User
      name={text('Name', 'Bester Kunde EUW')}
      kundennummer={text('Kundennummer', '834782346729')}
    />
  );
};

storiesOf('Generics | Kunde', module)
  .add('Default', () => <UserContainer />)
  .addDecorator(withKnobs);
