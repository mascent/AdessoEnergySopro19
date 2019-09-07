import AdessoEnergyApiComponent from './abstract-api-component';

class Authentication extends AdessoEnergyApiComponent {
  public async login(): Promise<string> {
    const result = await this.get('/api/login');

    if (!result.ok) {
      throw new Error('A problem occurred with the request.');
    }

    return await result.text();
  }

  public async logout() {}
}

export default Authentication;
