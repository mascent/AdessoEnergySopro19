import ApiConfig from './config';

abstract class AdessoEnergyApiComponent {
  protected readonly config: ApiConfig;

  constructor(config: ApiConfig) {
    this.config = config;
  }

  protected async get(endpoint: string): Promise<Response> {
    return await fetch(this.config.baseUrl + endpoint, {
      method: 'GET',
      mode: 'cors',
      redirect: 'error',
      headers: {
        Authorization: `Basic ${this.config.token}`,
        'Content-Type': 'application/json'
      }
    });
  }

  protected async put(endpoint: string, body: object): Promise<Response> {
    return await fetch(this.config.baseUrl + endpoint, {
      method: 'PUT',
      mode: 'cors',
      redirect: 'error',
      headers: {
        Authorization: `Basic ${this.config.token}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(body)
    });
  }

  protected async post(endpoint: string, body: object): Promise<Response> {
    return await fetch(this.config.baseUrl + endpoint, {
      method: 'PUT',
      mode: 'cors',
      redirect: 'error',
      headers: {
        Authorization: `Basic ${this.config.token}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(body)
    });
  }

  protected async delete(endpoint: string): Promise<Response> {
    return await fetch(this.config.baseUrl + endpoint, {
      method: 'DELETE',
      mode: 'cors',
      redirect: 'error',
      headers: {
        Authorization: `Basic ${this.config.token}`,
        'Content-Type': 'application/json'
      }
    });
  }
}

export default AdessoEnergyApiComponent;
