import { UserDTO, MeterDTO, ReadingDTO, IssueDTO } from '../typings/dtos';
import {
  User,
  Meter,
  Status,
  Reading,
  Issue
} from '../typings/provider-data-interfaces';

function addInitialStatus(): Status {
  return {
    changed: false,
    saving: false,
    saveError: null
  };
}

interface DateObjects {
  createdAt: Date;
  updatedAt: Date | null;
  deletedAt: Date | null;
}

function mapDatesToObjects(
  createdAt: string,
  updatedAt: string | null,
  deletedAt: string | null
): DateObjects {
  return {
    createdAt: new Date(createdAt),
    updatedAt: updatedAt ? new Date(updatedAt) : null,
    deletedAt: deletedAt ? new Date(deletedAt) : null
  };
}

export function mapUserDtoToUser(user: UserDTO): User {
  return {
    ...user,
    id: user.id.toString(),
    customerId: user.customerNumber,
    status: addInitialStatus(),
    ...mapDatesToObjects(user.createdAt, user.updatedAt, user.deletedAt)
  };
}

export function mapReadingDTOtoReading(reading: ReadingDTO): Reading {
  return {
    ...reading,
    status: addInitialStatus(),
    ...mapDatesToObjects(
      reading.createdAt,
      reading.updatedAt,
      reading.deletedAt
    )
  };
}

export function mapMeterDtoToMeter(meter: MeterDTO): Meter {
  return {
    ...meter,
    lastReading: mapReadingDTOtoReading(meter.lastReading),
    status: addInitialStatus(),
    ...mapDatesToObjects(meter.createdAt, meter.updatedAt, meter.deletedAt)
  };
}

export function mapIssueDtoToIssues(issue: IssueDTO): Issue {
  return {
    ...issue,
    state: issue.status,
    status: addInitialStatus(),
    ...mapDatesToObjects(issue.createdAt, issue.updatedAt, issue.deletedAt)
  };
}

export function mapInternalUserToUserDTO(
  user: Partial<User>
): Partial<UserDTO> {
  const res = {
    ...user,
    customerNumber: user.customerId,
    id: user.id ? parseInt(user.id, 10) : undefined,
    createdAt: user.createdAt && user.createdAt.toISOString(),
    updatedAt: user.updatedAt && user.updatedAt.toISOString(),
    deletedAt: user.deletedAt && user.deletedAt.toISOString()
  };
  delete res.status;
  return res;
}

export function mapInternalReadingToReadingDTO(
  reading: Partial<Reading>
): Partial<ReadingDTO> {
  const res = {
    ...reading,
    createdAt: reading.createdAt && reading.createdAt.toISOString(),
    updatedAt: reading.updatedAt && reading.updatedAt.toISOString(),
    deletedAt: reading.deletedAt && reading.deletedAt.toISOString()
  };
  delete res.status;
  return res;
}

function mapReadingIntoReadingDTO(r: Reading): ReadingDTO {
  const res = {
    ...r,
    createdAt: r.createdAt.toISOString(),
    updatedAt: r.updatedAt && r.updatedAt.toISOString(),
    deletedAt: r.deletedAt && r.deletedAt.toISOString()
  };
  delete res.status;
  return res;
}

export function mapInternalMeterToMeterDTO(
  meter: Partial<Meter>
): Partial<MeterDTO> {
  const res = {
    ...meter,
    lastReading:
      meter.lastReading && mapReadingIntoReadingDTO(meter.lastReading),
    createdAt: meter.createdAt && meter.createdAt.toISOString(),
    updatedAt: meter.updatedAt && meter.updatedAt.toISOString(),
    deletedAt: meter.deletedAt && meter.deletedAt.toISOString()
  };
  delete res.lastReading;
  delete res.status;
  return res;
}

export function mapInternalIssueToIssueDTO(
  issue: Partial<Issue>
): Partial<IssueDTO> {
  const res = {
    ...issue,
    status: issue.state,
    createdAt: issue.createdAt && issue.createdAt.toISOString(),
    updatedAt: issue.updatedAt && issue.updatedAt.toISOString(),
    deletedAt: issue.deletedAt && issue.deletedAt.toISOString()
  };
  delete res.state;
  return res;
}
