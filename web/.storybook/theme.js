import { create } from '@storybook/theming';
import logo from './logo.svg';

export default create({
  base: 'light',

  colorPrimary: '#2680C2',
  colorSecondary: '#F0B429',

  brandTitle: 'Adesso Energy Storybook',
  brandUrl: 'https://adesso.energy',
  brandImage: logo
});
