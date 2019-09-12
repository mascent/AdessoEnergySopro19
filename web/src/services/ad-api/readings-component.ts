import AdessoEnergyApiComponent from './abstract-api-component';
import { ReadingDTO } from '../../typings/dtos';
import { buildList, buildReadingDTO } from '../../utils/fake-builder';

class ReadingComponent extends AdessoEnergyApiComponent {
  public async getAllReadingsByAMeter(id: string): Promise<ReadingDTO[]> {
    return buildList(buildReadingDTO, 5, 100);
    // const result = await this.get(`/api/meters/${id}/readings`);

    // if (!result.ok) {
    //   throw new Error('A problem occurred with the request.');
    // }

    // return await result.json();
  }

  public async addReadingToAMeter(
    id: string,
    reading: Partial<ReadingDTO>
  ): Promise<ReadingDTO> {
    return buildReadingDTO(reading);
    // const result = await this.post(`/api/meters/${id}/readings`, reading);

    // if (!result.ok) {
    //   throw new Error('A problem occurred with the request.');
    // }

    // return await result.json();
  }

  public async updateReadingForAMeter(
    meterID: string,
    readingID: string,
    update: Partial<ReadingDTO>
  ): Promise<ReadingDTO> {
    return buildReadingDTO(update);
    // const result = await this.put(
    //   `/api/meters/${meterID}/readings/${readingID}`,
    //   update
    // );

    // if (!result.ok) {
    //   throw new Error('A problem occurred with the request.');
    // }

    // return await result.json();
  }
}

export default ReadingComponent;
