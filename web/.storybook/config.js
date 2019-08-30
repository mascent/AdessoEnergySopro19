import { configure, addDecorator, addParameters } from '@storybook/react';
import { withA11y } from '@storybook/addon-a11y';
import theme from './theme';

addParameters({
  options: {
    theme
  }
});

// Load all stories. They can be located anywhere inside /src. We want to keep
// our stories locally next to the component they belong to.
const req = require.context('../src', true, /\.stories\.(js|tsx)$/);
function loadStories() {
  req.keys().forEach(filename => req(filename));
}

addDecorator(withA11y);

configure(loadStories, module);
