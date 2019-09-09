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
    lastFetched: new Date(),
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
