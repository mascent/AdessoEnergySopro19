import React from 'react';
import { storiesOf } from '@storybook/react';
import { withKnobs, text } from '@storybook/addon-knobs';
import { Span, Paragraph, SectionHeader, SubTitle, Title } from './text';

storiesOf('Generics | Text', module)
  .addParameters({ jest: ['text'] })
  .addDecorator(withKnobs)
  .add('default', () => (
    <div>
      <Span>{text('Content', 'This is an example text for comparison')}</Span>
      <Paragraph>{text('Content', 'Simple span')}</Paragraph>
      <SectionHeader>{text('Content', 'Simple span')}</SectionHeader>
      <SubTitle>{text('Content', 'Simple span')}</SubTitle>
      <Title>{text('Content', 'Simple span')}</Title>
    </div>
  ));
