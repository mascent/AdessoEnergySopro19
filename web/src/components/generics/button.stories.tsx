import React from 'react';
import { storiesOf } from '@storybook/react';
import { PrimaryButton, SecondaryButton } from './button';
import { action } from '@storybook/addon-actions';
import { withKnobs, text, boolean } from '@storybook/addon-knobs';

storiesOf('Generics | PrimaryButton', module)
  .addParameters({ jest: ['button'] })
  .addDecorator(withKnobs)
  .add('Default', () => (
    <PrimaryButton
      disabled={boolean('Disabled', false)}
      onClick={action('clicked')}
    >
      {text('Content', 'PrimaryButton')}
    </PrimaryButton>
  ));

storiesOf('Generics | SecondaryButton', module)
  .addParameters({ jest: ['button'] })
  .addDecorator(withKnobs)
  .add('Default', () => (
    <SecondaryButton
      disabled={boolean('Disabled', false)}
      onClick={action('clicked')}
    >
      {text('Content', 'SecondaryButton')}
    </SecondaryButton>
  ));
