import AdessoEnergyApiComponent from './abstract-api-component';
import { LoginDTO } from '../../typings/dtos';

class Authentication extends AdessoEnergyApiComponent {
  public async login(): Promise<LoginDTO> {
    const result = await this.get('/api/login');

    if (!result.ok) {
      throw new Error('A problem occurred with the request.');
    }

    return await result.json();
  }

  public async logout() {}
}

export default Authentication;
