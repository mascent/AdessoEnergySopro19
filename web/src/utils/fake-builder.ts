/* This file contains builders for all dto's and internal objects.
 * These can be used in tests or stories.
 * If needed they can even be used to fake the api while testing.
 */

import faker from 'faker';
import {
  Meter,
  Reading,
  Status,
  User,
  Issue
} from '../typings/provider-data-interfaces';
import { MeterDTO, ReadingDTO, UserDTO, IssueDTO } from '../typings/dtos';
import {
  mapMeterDtoToMeter,
  mapReadingDTOtoReading,
  mapUserDtoToUser,
  mapIssueDtoToIssues
} from '../lib/mappers';

function buildStatus(overrides?: Partial<Status>): Status {
  return {
    saving: faker.random.boolean(),
    changed: faker.random.boolean(),
    lastFetched: faker.date.recent(),
    saveError: null,
    ...overrides
  };
}

/**
 * Build a meterDto object that can be used in tests or stories.
 * @param overrides Override for random values
 */
export function buildMeterDTO(overrides?: Partial<MeterDTO>): MeterDTO {
  return {
    id: faker.random.uuid(),
    meterNumber: faker.random.number({ min: 1000000000000000 }).toString(),
    ownerId: faker.random.uuid(),
    name: faker.company.companyName(),
    lastReading: buildReadingDTO(),
    type: faker.random.arrayElement(['gas', 'water', 'electricity']),
    createdAt: faker.date.recent().toISOString(),
    updatedAt: faker.date.recent().toISOString(),
    deletedAt: null,
    ...overrides
  };
}

/**
 * Build a meter object that can be used in tests or stories.
 * @param overrides Override for random values
 */
export function buildMeter(overrides?: Partial<Meter>): Meter {
  return { ...mapMeterDtoToMeter(buildMeterDTO()), ...overrides };
}

/**
 * Build a readingDto object that can be used in tests or stories.
 * @param overrides Override for random values
 */
export function buildReadingDTO(overrides?: Partial<ReadingDTO>): ReadingDTO {
  return {
    id: faker.random.uuid(),
    meterId: faker.random.uuid(),
    ownerId: faker.random.uuid(),
    lastEditorName: faker.name.firstName() + ' ' + faker.name.lastName(),
    lastEditReason: faker.lorem.words(5),
    trend: faker.random.number({ min: -10, max: 10 }),
    value: faker.random.number({ min: 1000000 }).toString(),
    createdAt: faker.date.recent().toISOString(),
    updatedAt: faker.date.recent().toISOString(),
    deletedAt: null,
    ...overrides
  };
}

/**
 * Build a reading object that can be used in tests or stories.
 * @param overrides Override for random values
 */
export function buildReading(overrides?: Partial<Reading>): Reading {
  return { ...mapReadingDTOtoReading(buildReadingDTO()), ...overrides };
}

/**
 * Build a userDto object that can be used in tests or stories.
 * @param overrides Override for random values
 */
export function buildUserDTO(overrides?: Partial<UserDTO>): UserDTO {
  return {
    id: faker.random.uuid(),
    customerId: faker.random.uuid(),
    email: faker.internet.email(),
    firstName: faker.name.firstName(),
    lastName: faker.name.lastName(),
    createdAt: faker.date.recent().toISOString(),
    updatedAt: faker.date.recent().toISOString(),
    deletedAt: null,
    ...overrides
  };
}

/**
 * Build a user object that can be used in tests or stories.
 * @param overrides Override for random values
 */
export function buildUser(overrides?: Partial<User>): User {
  return { ...mapUserDtoToUser(buildUserDTO()), ...overrides };
}

export function buildIssueDTO(overrides?: Partial<IssueDTO>): IssueDTO {
  return {
    id: faker.random.uuid(),
    email: faker.internet.email(),
    name: faker.name.firstName() + ' ' + faker.name.lastName(),
    message: faker.lorem.paragraph(),
    status: faker.random.arrayElement(['UNRESOLVED', 'RESOLVED']),
    subject: faker.lorem.words(5),
    createdAt: faker.date.recent().toISOString(),
    updatedAt: faker.date.recent().toISOString(),
    deletedAt: null,
    ...overrides
  };
}

export function buildIssue(overrides?: Partial<Issue>): Issue {
  return { ...mapIssueDtoToIssues(buildIssueDTO()), ...overrides };
}

export function buildList<T>(
  builder: (overrides?: Partial<T>) => T,
  min: number = 0,
  max: number = 10
) {
  const length = faker.random.number({ min, max });
  const arr = [];

  for (let i = 0; i < length; i++) {
    arr.push(builder());
  }
}
