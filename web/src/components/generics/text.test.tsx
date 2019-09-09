import React from 'react';
import { render } from '@testing-library/react';
import { Span, Paragraph, SectionHeader, SubTitle, Title } from './text';

test('Span components renders children', () => {
  const { getByText } = render(<Span>Test</Span>);
  expect(getByText('Test')).toBeVisible();
});

test('Span components accepts a optional className', () => {
  const { getByText } = render(<Span className="Test">Test</Span>);

  const text = getByText('Test');
  expect(text.classList.contains('Test')).toBeTruthy();
});

test('Paragraph components renders children', () => {
  const { getByText } = render(<Paragraph>Test</Paragraph>);
  expect(getByText('Test')).toBeVisible();
});

test('Paragraph components accepts a optional className', () => {
  const { getByText } = render(<Paragraph className="Test">Test</Paragraph>);

  const text = getByText('Test');
  expect(text.classList.contains('Test')).toBeTruthy();
});

test('SectionHeader components renders children', () => {
  const { getByText } = render(<SectionHeader>Test</SectionHeader>);
  expect(getByText('Test')).toBeVisible();
});

test('SectionHeader components accepts a optional className', () => {
  const { getByText } = render(
    <SectionHeader className="Test">Test</SectionHeader>
  );

  const text = getByText('Test');
  expect(text.classList.contains('Test')).toBeTruthy();
});

test('SubTitle components renders children', () => {
  const { getByText } = render(<SubTitle>Test</SubTitle>);
  expect(getByText('Test')).toBeVisible();
});

test('SubTitle components accepts a optional className', () => {
  const { getByText } = render(<SubTitle className="Test">Test</SubTitle>);

  const text = getByText('Test');
  expect(text.classList.contains('Test')).toBeTruthy();
});

test('Title components renders children', () => {
  const { getByText } = render(<Title>Test</Title>);
  expect(getByText('Test')).toBeVisible();
});

test('Title components accepts a optional className', () => {
  const { getByText } = render(<Title className="Test">Test</Title>);

  const text = getByText('Test');
  expect(text.classList.contains('Test')).toBeTruthy();
});
