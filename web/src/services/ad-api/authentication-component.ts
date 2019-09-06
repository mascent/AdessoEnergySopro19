import AdessoEnergyApiComponent from './abstract-api-component';

class Authentication extends AdessoEnergyApiComponent {
  public async login(username: string, password: string): Promise<string> {
    // This will not work, because this.post requires credentials. However
    // we do not have credentials. That is the reason why we want to login...
    const result = await this.post('/api/login', { username, password });

    if (!result.ok) {
      throw new Error('A problem occurred with the request.');
    }

    return await result.text();
  }

  public async logout() {}
}

export default Authentication;
