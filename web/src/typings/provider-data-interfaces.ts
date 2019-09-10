export type MeterType = 'water' | 'electricity' | 'gas';

export interface Status {
  saving: boolean;
  saveError: Error | null;
  changed: boolean;
  lastFetched: Date;
}

export interface User {
  id: string;
  customerId: string;
  firstName: string;
  lastName: string;
  email: string;
  status: Status;
  createdAt: Date;
  updatedAt: Date | null;
  deletedAt: Date | null;
}

export interface Meter {
  id: string;
  type: MeterType;
  name: string;
  ownerId: string | null;
  lastReading: Reading;
  meterNumber: string;
  status: Status;
  createdAt: Date;
  updatedAt: Date | null;
  deletedAt: Date | null;
}

export interface Reading {
  id: string;
  meterId: string;
  ownerId: string;
  value: string;
  trend: number;
  lastEditorName: string;
  lastEditReason: string;
  status: Status;
  createdAt: Date;
  updatedAt: Date | null;
  deletedAt: Date | null;
}

export interface Issue {
  id: string;
  email: string;
  name: string;
  subject: string;
  message: string;
  state: 'UNRESOLVED' | 'RESOLVED';
  status: Status;
  createdAt: Date;
  updatedAt: Date | null;
  deletedAt: Date | null;
}
