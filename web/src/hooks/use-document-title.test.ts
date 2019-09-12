import { renderHook } from '@testing-library/react-hooks';
import useDocumentTitle from './use-document-title';

const expectedBase = ' - Adesso Energy';

test('title is set to "Adesso Energy if an empty title is provided"', () => {
  renderHook(() => useDocumentTitle(''));
  expect(document.title).toBe('Adesso Energy');

  renderHook(() => useDocumentTitle('    '));
  expect(document.title).toBe('Adesso Energy');
});

test('title is set to the provided value + "- Adesso Energy"', () => {
  const spy = jest.spyOn(document, 'title', 'set');

  renderHook(() => useDocumentTitle('Test'));
  const title = document.title;

  expect(spy).toHaveBeenCalledTimes(1);
  expect(title).toBe('Test' + expectedBase);

  spy.mockRestore();
});

test('whitespaces around the title are ignored', () => {
  const spy = jest.spyOn(document, 'title', 'set');

  renderHook(() => useDocumentTitle('  Test '));
  const title = document.title;

  expect(spy).toHaveBeenCalledTimes(1);
  expect(title).toBe('Test' + expectedBase);

  spy.mockRestore();
});

test('title is not set twice if it does not changes during rerender', () => {
  const spy = jest.spyOn(document, 'title', 'set');

  const { rerender } = renderHook(() => useDocumentTitle('Test'));

  expect(spy).toHaveBeenCalledTimes(1);
  expect(document.title).toBe('Test' + expectedBase);

  rerender();

  expect(spy).toHaveBeenCalledTimes(1);
  expect(document.title).toBe('Test' + expectedBase);

  spy.mockRestore();
});

test('title is updates if it does change in a rerender', () => {
  const spy = jest.spyOn(document, 'title', 'set');

  const { rerender } = renderHook(title => useDocumentTitle(title), {
    initialProps: 'Test'
  });

  expect(spy).toHaveBeenCalledTimes(1);
  expect(document.title).toBe('Test' + expectedBase);

  spy.mockClear();
  rerender('Second Test');

  expect(spy).toHaveBeenCalledTimes(1);
  expect(document.title).toBe('Second Test' + expectedBase);

  spy.mockRestore();
});
