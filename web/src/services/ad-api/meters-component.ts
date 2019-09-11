import AdessoEnergyApiComponent from './abstract-api-component';
import { MeterDTO } from '../../typings/dtos';
import { buildList, buildMeterDTO } from '../../utils/fake-builder';

class MetersComponent extends AdessoEnergyApiComponent {
  public async getMetersForUser(id: string): Promise<MeterDTO[]> {
    return buildList(buildMeterDTO, 5, 100);
    // const result = await this.get(`/api/users/${id}/meters`);

    // if (!result.ok) {
    //   throw new Error('A problem occurred with the request.');
    // }

    // return await result.json();
  }

  public async createNewMeter(meter: Partial<MeterDTO>): Promise<MeterDTO> {
    return buildMeterDTO(meter);
    // const result = await this.post('/api/meters', meter);

    // if (!result.ok) {
    //   throw new Error('A problem occurred with the request.');
    // }

    // return await result.json();
  }

  public async updateMeter(
    id: string,
    meter: Partial<MeterDTO>
  ): Promise<MeterDTO> {
    return buildMeterDTO(meter);
    // const result = await this.put(`/api/meters/${id}`, meter);

    // if (!result.ok) {
    //   throw new Error('A problem occurred with the request.');
    // }

    // return await result.json();
  }
}

export default MetersComponent;
