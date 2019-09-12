import React from 'react';
import ReactDOM from 'react-dom';
import App from './app';
import './index.scss';
import { AuthenticationProvider } from './providers/authentication-provider';
import { config } from './services/ad-api';
import { getApiBaseDomain } from './lib/app-config';

config.init({
  baseUrl: getApiBaseDomain()
});

ReactDOM.render(
  <AuthenticationProvider>
    <App />
  </AuthenticationProvider>,
  document.getElementById('root')
);
