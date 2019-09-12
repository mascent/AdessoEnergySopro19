import AdessoEnergyApiComponent from './abstract-api-component';
import { UserDTO } from '../../typings/dtos';

class UsersComponent extends AdessoEnergyApiComponent {
  public async getAllUsers(): Promise<UserDTO[]> {
    const result = await this.get('/api/users');

    if (!result.ok) {
      throw new Error('A problem occurred with the request.');
    }

    return await result.json();
  }

  public async createNewUser(user: Partial<UserDTO>): Promise<UserDTO> {
    const result = await this.post('/api/users', user);

    if (!result.ok) {
      throw new Error('A problem occurred with the request.');
    }

    return await result.json();
  }

  public async updateUser(
    id: string,
    update: Partial<UserDTO>
  ): Promise<UserDTO> {
    const result = await this.put(`/api/users/${id}`, update);

    if (!result.ok) {
      throw new Error('A problem occurred with the request.');
    }

    return await result.json();
  }
}

export default UsersComponent;
