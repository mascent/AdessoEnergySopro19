import React, { StrictMode } from 'react';
import ReactDOM from 'react-dom';
import App from './app';
import * as serviceWorker from './service-worker';
import './index.scss';
import { AuthenticationProvider } from './providers/authentication-provider';
import { config } from './services/ad-api';
import { getApiBaseDomain } from './lib/app-config';

config.init({
  clientId: 'do we have a client id?',
  baseUrl: getApiBaseDomain()
});

ReactDOM.render(
  <StrictMode>
    <AuthenticationProvider>
      <App />
    </AuthenticationProvider>
  </StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
