import AdessoEnergyApiComponent from './abstract-api-component';
import { Paging, IssueDTO } from '../../typings/dtos';

class IssueComponent extends AdessoEnergyApiComponent {
  public async getAllIssues(): Promise<Paging<IssueDTO>> {
    const result = await this.get('/api/issues');

    if (!result.ok) {
      throw new Error('A problem occurred with the request.');
    }

    return await result.json();
  }

  public async createNewIssue(
    issue: Partial<IssueDTO>,
    id: string
  ): Promise<IssueDTO> {
    const result = await this.post(`/api/issues/${id}`, issue);

    if (!result.ok) {
      throw new Error('A problem occurred with the request.');
    }

    return await result.json();
  }

  public async updateIssue(
    id: string,
    update: Partial<IssueDTO>
  ): Promise<IssueDTO> {
    const result = await this.put(`/api/issues/${id}`, update);

    if (!result.ok) {
      throw new Error('A problem occurred with the request.');
    }

    return await result.json();
  }
}

export default IssueComponent;
